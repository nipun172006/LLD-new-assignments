# Java Low-Level Design Case Studies

Five standalone Java models for exploring object boundaries, strategies,
factories, state, scheduling, and domain services.

## Case studies

| Module | Design focus |
|---|---|
| `Elevator/` | Scheduling strategies, car state, floor requests, and service coordination |
| `MovieBooking/` | Shows, screens, seats, tickets, and booking service boundaries |
| `SnakeAndLadder/` | Board/game separation and configurable turn strategies |
| `parkingLot/` | Slot allocation, pricing strategies, gates, vehicles, and tickets |
| `pen/` | Interfaces, concrete pen types, refills, and factory-based construction |

Each module has its own `Main.java` entry point and intentionally remains
independent from the others.

## Validate the examples

The modules reuse class names, so they must be compiled separately. The check
script creates isolated temporary output directories for all five:

```bash
scripts/check.sh
```

To run one case study manually:

```bash
cd Elevator
javac Main.java components/*.java enums/*.java models/*.java scheduling/*.java services/*.java
java Main
```

## Course context

These case studies originated as low-level-design coursework. The examples and
history retain that context; they are educational models rather than claims of
production deployments.

## Limitations

- State is in memory and the examples omit persistence, networking, and
  multi-process coordination.
- Compilation checks structure and type correctness; behavioral tests are a
  future improvement.
- The case studies remain separate from `SST28-LLD101`; their histories have not
  been consolidated or discarded.
- No license is asserted while coursework ownership remains under review.
