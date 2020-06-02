class dpcharge:
    def __init__(self, T: list, Coins: list, money: int):
        n = len(Coins)
        bag = [999999 for i in range(money+1)]
        bag[0] = 0
        for i in range(n-1, 0, -1):
            for j in range(1, Coins[i]+1):
                for k in range(m, T[i]-1, -1):
                    bag[k] = min(bag[k], bag[k-T[i]]+1)
        if bag[m] < m:
            print('共需要', bag[m], '枚硬币')
            # self.tarceback(T,Coins,m,n,bag[m])
        else:
            print("Can't charge!")


class dpcharge1:
    def __init__(self, T: list, Coins: list, money: int):
        n = len(Coins)
        bag = [999999 for i in range(money+1)]
        bag[0] = 0
        for i in range(1, n):
            for j in range(1, Coins[i]+1):
                for k in range(m, T[i]-1, -1):
                    bag[k] = min(bag[k], bag[k-T[i]]+1)
        if bag[m] < m:
            print('共需要', bag[m], '枚硬币')
            # self.tarceback(T,Coins,m,n,bag[m])
        else:
            print("Can't charge!")


Coins = [0, 3, 7, 9, 2, 1]
T = [0, 1, 2, 4, 5, 10]
for m in range(3, 70):
    print(m)
    a = dpcharge(T, Coins, m)
    b = dpcharge1(T, Coins, m)
