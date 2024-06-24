# Wasp Game in Scala

Create a simple text-based game that can be played in the command line. The game involves hitting random wasps from a nest of wasps. Each time a command is given to hit a wasp, a random wasp is selected, and its hit points are reduced. Once a wasp's hit points reach zero, it dies and cannot be hit again.

The state of all the wasps should be displayed after each hit. The game is over when all wasps have died. The user should be able to start a new game once the game is over.

## Game Rules

You start the game with the following wasps:

### 1 x Queen

- 80 Hit Points
- Loses 7 hit points every time it is hit
- **All wasps die if the queen is killed**

### 5 x Worker Wasps

- Each one starts with 68 hit points
- Each one loses 10 hit points each time it is hit

### 8 x Drone Wasps

- Each starts with 60 hit points
- Each loses 12 hit points each time it is hit

**Game Flow**:

- The user types `fire` command to hit a random wasp.
- The state of all the wasps is displayed after each hit.
- The game ends when all wasps are dead.
    - If the Queen Wasp is killed, all wasps die immediately, and the game ends.
- The user should be able to restart the game once it ends.

**Commands**:

- `fire`: Hits a random wasp.
- `restart`: Restarts the game after it has ended.
- `quit`: Exits the game.

---

EXTENSION:

- Write the results of a game to a csv file?
    - player-name,time-taken,shots-fired
- Auto fire?

---