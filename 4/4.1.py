'''
>>>
4 7 
2 5 
8 10
6 18
>>>
2
'''
class Meeting:
    def __init__(self,s:int,e:int,r:int):
        self.s=s
        self.e=e
        self.r=r
        
class Greedy():
    def assign(self, meet:list, n:int):
        meet.sort(key=lambda x: x.s)
        for room in range(1, n+1):
            end=0
            for i in range(n):
                if meet[i].s>=end and meet[i].r==-1:
                    end=meet[i].e
                    meet[i].r=room
                else:
                    continue
        meet.sort(key=lambda x: x.r)
        print("需要",meet[n-1].r,"个会场")



n=eval(input("请输入活动数"))
ans=[[] for i in range (n)]
for i in range(n):
    s=eval(input("请输入开始时间："))
    e=eval(input("请输入结束时间："))
    r=-1
    ans[i]=Meeting(s,e,r)
res=Greedy()
res.assign(ans,n)
print("具体安排：")
for i in range(n):
    print("start:",ans[i].s,"end:",ans[i].e,"room",ans[i].r)