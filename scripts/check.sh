#!/usr/bin/env bash
set -euo pipefail

repo_root=$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)
compile_root=$(mktemp -d "${TMPDIR:-/tmp}/java-lld-compile.XXXXXX")
trap 'rm -rf "$compile_root"' EXIT

modules=(Elevator MovieBooking SnakeAndLadder parkingLot pen)

for module_name in "${modules[@]}"; do
  source_dir="$repo_root/$module_name"
  module_out="$compile_root/$module_name"
  source_list="$compile_root/$module_name.sources"

  mkdir -p "$module_out"
  find "$source_dir" -name '*.java' -print | sort > "$source_list"
  javac -d "$module_out" @"$source_list"
  printf 'Compiled %s\n' "$module_name"
done
