#连接数据库
import datetime

import pymysql

db = pymysql.connect(password="admin123456",database="cilicili",user="root")
cursor = db.cursor()

sql = '''insert into ucenter_member(
id,
password,
nickname,
sex,
age,
gmt_create,
gmt_modified,
mobile)
values('{}','{}','{}','{}','{}','{}','{}','{}')'''

###############################################################################################


f = open("ml-100k/u.user")
line = f.readline()
map = {}
date_time = "2023-04-25 11:28:21"
mobile = 4008517517
while(line):
    print(line)
    id = line.split("|")[0]
    job = line.split("|")[3]
    # print(job)
    if(map.get(job) ==None):
        map[job] = 1
        count = 1
    else:
        count = map.get(job)
        map[job] +=1
    name = job+str(count)
    print(name)
    age = line.split("|")[1]
    sexMF = line.split("|")[2]
    if(sexMF == "F"):
        sex = 0
    else:sex = 1
    print("sex="+str(sex))
    print(id)
    cursor.execute(sql.format(id, "e10adc3949ba59abbe56e057f20f883e",name,sex,age,date_time,date_time,mobile+eval(id)))
    print(sql.format(id, "e10adc3949ba59abbe56e057f20f883e",name,sex,age,date_time,date_time,mobile+eval(id)))

    line = f.readline()

#####################################################################################
db.commit()
# 关闭数据库连接
db.close()

