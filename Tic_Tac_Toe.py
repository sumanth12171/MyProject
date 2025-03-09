import os
import random
board = [[' ' for _ in range(3)] for _ in range(3)]
turns = 9
used = []
def place(num, mark):
    global board
    x = (num-1) // 3
    y = (num-1) % 3
    board[x][y] = mark
def mark(turn):
    return 'O' if turn % 2 == 0 else 'X'
def row():
    for r in range(3):
        if board[r][0] == board[r][1] == board[r][2] != ' ':
            return board[r][0]
    return 0
def column():
    for c in range(3):
        if board[0][c] == board[1][c] == board[2][c] != ' ':
            return board[0][c]
    return 0


def cross():
    if board[0][0] == board[1][1] == board[2][2] != ' ':
        return board[0][0]
    if board[0][2] == board[1][1] == board[2][0] != ' ':
        return board[0][2]
    return 0


def show():
    os.system('cls' if os.name == 'nt' else 'clear')
    print("\n\n--- Tic Tac Toe ---\n\n")
    print("Player (O)  -  Computer (X)\n\n")
    print("_____________________")
    for r in range(3):
        print("\n     |     |     ")
        for c in range(3):
            print(f"  {board[r][c]}   ", end='')
        print("\n_____|_____|_____")


def minimax(isMax):
    winner = row() or column() or cross()
    if winner == 'X':
        return 1
    if winner == 'O':
        return -1
    if turns == 0:
        return 0

    if isMax:
        best = -1000
        for i in range(1, 10):
            if i not in used:
                x, y = (i-1)//3, (i-1)%3
                board[x][y] = 'X'
                used.append(i)
                best = max(best, minimax(False))
                board[x][y] = ' '
                used.remove(i)
        return best
    else:
        best = 1000
        for i in range(1, 10):
            if i not in used:
                x, y = (i-1)//3, (i-1)%3
                board[x][y] = 'O'
                used.append(i)
                best = min(best, minimax(True))
                board[x][y] = ' '
                used.remove(i)
        return best


def bestMove():
    best = -1000
    best_move = -1
    for i in range(1, 10):
        if i not in used:
            x, y = (i-1)//3, (i-1)%3
            board[x][y] = 'X'
            used.append(i)
            score = minimax(False)
            board[x][y] = ' '
            used.remove(i)
            if score > best:
                best = score
                best_move = i
    return best_move


while True:
    show()
    while turns > 0:
        while True:
            try:
                spot = int(input("\n\nPlayer, choose your spot (1-9): "))
                if spot < 1 or spot > 9 or spot in used:
                    print("❌ Invalid Move. Try Again.")
                    continue
                break
            except ValueError:
                print("❌ Invalid Move. Please enter a number (1-9).")

        place(spot, 'O')
        used.append(spot)
        turns -= 1
        show()

        result = row() or column() or cross()
        if result == 'O':
            print("\n\n🎉 Player (O) WON!")
            break
        if turns == 0:
            break

        if random.randint(1, 100) > 60:
            available_moves = [i for i in range(1, 10) if i not in used]
            spot = random.choice(available_moves)
        else:
            spot = bestMove()

        place(spot, 'X')
        used.append(spot)
        turns -= 1
        show()

        result = row() or column() or cross()
        if result == 'X':
            print("\n\n Computer (X) WON!")
            break

    if turns == 0 and result == 0:
        print("\n\n It's a TIE. No winner.")

    restart = input("\n\nPlay again? (y/n): ").lower()
    if restart != 'y':
        break

    turns = 9
    used.clear()
    board = [[' ' for _ in range(3)] for _ in range(3)]
    os.system('cls' if os.name == 'nt' else 'clear')
