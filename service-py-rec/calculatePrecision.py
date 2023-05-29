
import  pandas as pd
heads=['user_id','item_id','rating','timestamp']
ratings=pd.read_csv(r'u.data',sep='\t',names=heads)
print(ratings)
print("评分数量",len(ratings))


u_cols=['user_id','age','sex','occupation','zip_code']
users=pd.read_csv(r'u.data',sep='|',names=u_cols,encoding='latin-1')
print(users)
r_cols=['user_id','movie_id','rating','unix_timestamp']
ratings=pd.read_csv(r'u.data',sep='\t',names=r_cols,encoding='latin-1')
print(ratings)
m_cols=['movie_id','title','release_data','video_release_data','imdb_url']
movies=pd.read_csv(r'u.item',sep='|',names=m_cols,usecols=range(5),encoding='latin-1')
print(movies)

import pandas as pd
import numpy as np
from sklearn.metrics.pairwise import pairwise_distances

np.set_printoptions(suppress=True)  # 取消科学计数法输出
pd.set_option('display.max_rows', None)  # 展示所有行
pd.set_option('display.max_columns', None)  # 展示所有列


def predict(scoredata, similarity, type='user'):
    # 基于物品得推荐
    if type == 'item':
        predt_mat = scoredata.dot(similarity) / np.array([np.abs(similarity).sum(axis=1)])
    elif type == 'user':
        # 计算用户评分值 减少用户评分高低习惯影响
        user_meanscorse = scoredata.mean(axis=1)
        score_diff = (scoredata - user_meanscorse.reshape(-1, 1))
        predt_mat = user_meanscorse.reshape(-1, 1) + similarity.dot(score_diff) / np.array(
            [np.abs(similarity).sum(axis=1)]).T
    return predt_mat


# 读取数据
print('step 1 读取数据')
r_cols = ['user_id', 'movie_id', 'rating', 'unix_timestamp']
scoredata = pd.read_csv(r'u.data', sep='\t', names=r_cols, encoding='latin-1')
print('数据形状', scoredata.shape)
# 生成用户-物品评分矩阵
print('step2 生成 用户物品评分矩阵')
n_users = 943
n_items = 1682
data_matrix = np.zeros((n_users, n_items))
for line in range(np.shape(scoredata)[0]):
    row = scoredata['user_id'][line] - 1
    col = scoredata['movie_id'][line] - 1
    score = scoredata['rating'][line]
    data_matrix[row, col] = score
print('用户物品矩阵形状', data_matrix.shape)
# 计算相似度
print('step3 计算相似度')
user_similaritry = pairwise_distances(data_matrix, metric='cosine')
item_similarity = pairwise_distances(data_matrix.T, metric='cosine')
print('user similarity', user_similaritry.shape)
print('item similartity', item_similarity.shape)
# 进行相似度进行预测
print('step4 预测')
user_prediction = predict(data_matrix, user_similaritry, type='user')
item_perdiction = predict(data_matrix, item_similarity, type='item')

# 显示推荐结果
print('step 5 显示推荐结果')
print('----------------')
print('ubcf预测形状', user_prediction.shape)
print('real answer\n', data_matrix[:5, 5])
print('预测结果\n', user_prediction)
print('ibcf预测形状', item_perdiction.shape)
print('real answer\n', data_matrix[:5, :5])
print('预测结果\n', item_perdiction)
# 性能评估
print('step 6 性能评估')
from sklearn.metrics import mean_squared_error
from math import sqrt


def rmse(predct, realNum):
    predct = predct[realNum.nonzero()].flatten()
    realNum = realNum[realNum.nonzero()].flatten()
    return sqrt(mean_squared_error(predct, realNum))


print('u-base mse=', str(rmse(user_prediction, data_matrix)))
print('m-based mse=', str(rmse(item_perdiction, data_matrix)))







