import random
def initialize_board():
    board = [["Save" for _ in range(4)] for _ in range(4)]
    wumpus_row, wumpus_col = random.randint(0, 3), random.randint(0, 3)
    board[wumpus_row][wumpus_col] = "WUMPUS"

    gold_row, gold_col = random.randint(0, 3), random.randint(0, 3)
    while (gold_row == wumpus_row and gold_col == wumpus_col):
        gold_row, gold_col = random.randint(0, 3), random.randint(0, 3)
    board[gold_row][gold_col] = "GOLD"

    for _ in range(2):
        pit_row, pit_col = random.randint(0, 3), random.randint(0, 3)
        while (board[pit_row][pit_col] in ["WUMPUS", "GOLD", "PIT"]):
            pit_row, pit_col = random.randint(0, 3), random.randint(0, 3)
        board[pit_row][pit_col] = "PIT"

    for r in range(4):
        for c in range(4):
            if board[r][c] == "PIT":
                if r > 0 and board[r - 1][c] != "PIT":
                    board[r - 1][c] = "Breeze"
                if r < 3 and board[r + 1][c] != "PIT":
                    board[r + 1][c] = "Breeze"
                if c > 0 and board[r][c - 1] != "PIT":
                    board[r][c - 1] = "Breeze"
                if c < 3 and board[r][c + 1] != "PIT":
                    board[r][c + 1] = "Breeze"

    for r in range(4):
        for c in range(4):
            if abs(r - wumpus_row) <= 1 and abs(c - wumpus_col) <= 1 and board[r][c] != "WUMPUS":
                if board[r][c] != "PIT":
                    board[r][c] = "Smell"

    return board, wumpus_row, wumpus_col, gold_row, gold_col


def print_grid(board, row, column):
    for r in range(4):
        for c in range(4):
            if r == row and c == column:
                print("P ", end="")
            else:
                print("X ", end="")
        print()


row, column = 0, 0
arrow = True
player = True
score = 0
board, wumpus_row, wumpus_col, gold_row, gold_col = initialize_board()

while player:
    print_grid(board, row, column)
    choice = input("Press 'u' to move up\nPress 'd' to move down\nPress 'l' to move left\nPress 'r' to move right\n")

    if choice == "u":
        if row > 0:
            row -= 1
        else:
            print("Move denied")
    elif choice == "d":
        if row < 3:
            row += 1
        else:
            print("Move denied")
    elif choice == "l":
        if column > 0:
            column -= 1
        else:
            print("Move denied")
    elif choice == "r":
        if column < 3:
            column += 1
        else:
            print("Move denied")
    else:
        print("Invalid choice")
        continue

    print("Current location:", board[row][column], "\n")

    if board[row][column] == "PIT":
        print("You fell into the pit!")
        print("Game Over!")
        player = False
        break

    if row == gold_row and column == gold_col:
        print("Congratulations! You found the GOLD and won the game!")
        print("Final Score:", score)
        player = False
        break

    if board[row][column] == "Breeze":
        print("You feel a Breeze... There's a Pit nearby!")

    if board[row][column] == "Smell" and arrow:
        arrow_choice = input("Do you want to throw an arrow?\nPress 'y' to throw\nPress 'n' to save your arrow\n")
        if arrow_choice == "y":
            arrow_throw = input("Press 'u' to throw up\nPress 'd' to throw down\nPress 'l' to throw left\nPress 'r' to throw right\n")

            if arrow_throw == "u" and row > 0:
                if row - 1 == wumpus_row and column == wumpus_col:
                    print("Wumpus killed!")
                    score += 1000
                    board[wumpus_row][wumpus_col] = "Save"
                else:
                    print("Arrow wasted...")
                    score -= 10
            elif arrow_throw == "d" and row < 3:
                if row + 1 == wumpus_row and column == wumpus_col:
                    print("Wumpus killed!")
                    score += 1000
                    board[wumpus_row][wumpus_col] = "Save"
                else:
                    print("Arrow wasted...")
                    score -= 10
            elif arrow_throw == "l" and column > 0:
                if row == wumpus_row and column - 1 == wumpus_col:
                    print("Wumpus killed!")
                    score += 1000
                    board[wumpus_row][wumpus_col] = "Save"
                else:
                    print("Arrow wasted...")
                    score -= 10
            elif arrow_throw == "r" and column < 3:
                if row == wumpus_row and column + 1 == wumpus_col:
                    print("Wumpus killed!")
                    score += 1000
                    board[wumpus_row][wumpus_col] = "Save"
                else:
                    print("Arrow wasted...")
                    score -= 10
            else:
                print("Invalid arrow direction or out of bounds.")
        else:
            print("Arrow saved")

    if row == wumpus_row and column == wumpus_col:
        print("You have been eaten by the Wumpus!")
        print("Game Over!")
        player = False
        print("Score:", score)

print("Game Over! Final Score:", score)
