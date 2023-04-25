from datetime import datetime

import pymysql

db = pymysql.connect(password="admin123456",database="cilicili",user="root")
cursor = db.cursor()



sqlCollection = '''insert into collection(
id,
v_id,
u_id,
gmt_create,
gmt_modified)
values('{}','{}','{}','{}','{}')'''

###############################################################################################


f = open("ml-100k/u.data")


###############################################################################################

line = f.readline()
id=1
while(line):
    now = datetime.now()
    date_time = now.strftime("%Y-%m-%d %H:%M:%S")
    print(line)
    part = line.split("\t")
    u_id = part[0]
    v_id = part[1]
    score = part[2]
    print("uid={},\n"
          "vid={}\n"
          "score={}".format(u_id,v_id,score))
    if int(score)>=3:
        print(sqlCollection.format(id,v_id,u_id,date_time,date_time))
        cursor.execute(sqlCollection.format(id,v_id,u_id,date_time,date_time))
        id+=1

    # print(sqlVideo.format(id, title, producer, classification, parentClassification, cover, date_time, date_time))
    # cursor.execute(sqlVideo.format(id,title,producer,classification,parentClassification,cover,date_time,date_time))
    #
    line = f.readline()

#############################################################################################
db.commit()
# 关闭数据库连接
db.close()
