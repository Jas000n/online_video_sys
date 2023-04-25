from datetime import datetime

import pymysql

db = pymysql.connect(password="admin123456",database="cilicili",user="root")
cursor = db.cursor()

sqlVideo = '''insert into video(
id,
title,
producer_id,
classification_id,
classification_parent_id,
cover,
gmt_create,
gmt_modified)
values('{}','{}','{}','{}','{}','{}','{}','{}')'''

sqlDescription = '''insert into video_description(
id,
description,
gmt_create,
gmt_modified)
values('{}','{}','{}','{}')'''

###############################################################################################


f = open("ml-100k/u.item")
classification = 2
parentClassification = 1
producer = 1
cover = "https://jason-cili.oss-cn-beijing.aliyuncs.com/2023/03/20/11aab5255ca04154a3d19c560a5471c0_cat2.jpeg"
###############################################################################################

line = f.readline()
while(line):
    #print(line)
    now = datetime.now()
    date_time = now.strftime("%Y-%m-%d %H:%M:%S")
    #print(date_time)

    part = line.split("|")
    id = part[0]
    title = part[1]
    title = title[0:18]
    title = title.replace("'", "")
    print(title)
    description = part[4]
    description = description.replace("'","")
    # print("id={},\n"
    #       "title={}\n"
    #       "description={}".format(id,title,description))
    print(sqlVideo.format(id, title, producer, classification, parentClassification, cover, date_time, date_time))
    cursor.execute(sqlVideo.format(id,title,producer,classification,parentClassification,cover,date_time,date_time))
    #cursor.execute(sqlDescription.format(id,description,date_time,date_time))
    line = f.readline()

##############################################################################################
db.commit()
# 关闭数据库连接
db.close()
