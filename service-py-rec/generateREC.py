import mv100
import numpy as np
def cos_sim(x, y):
    '''余弦相似性
    input:  x(mat):以行向量的形式存储，可以是用户或者商品
            y(mat):以行向量的形式存储，可以是用户或者商品
    output: x和y之间的余弦相似度
    '''
    numerator = x.dot(y)  # x和y之间的额积
    denominator = (np.sqrt(x * x.T) * np.sqrt(y * y.T))[0]
    return (numerator / denominator)


def similarity(data,userId):
    '''计算用户user和其他用户的相似度
    input:  data(mat):任意矩阵
            userId(int):用户id
    output: w(mat):任意两行之间的相似度
    '''
    user_list = list(data[:,0])
    user_index = user_list.index(userId)
    data = data[1:][1:]
    m = np.shape(data)[0]  # 用户的数量
    # 初始化相似度矩阵
    w = np.mat(np.zeros((m, m)))

    i = user_index
    for j in range(i, m):
        if j != i:
            # 计算任意两行之间的相似度
            w[i, j] = cos_sim(data[i,], data[j,])
            w[j, i] = w[i, j]
        else:
            w[i, j] = 0

    return w


def user_based_recommend(data, w, user):
    '''基于用户相似性为用户user推荐商品, *******注意这里的user是id*********
    input:  data(mat):用户商品矩阵
            w(mat):用户之间的相似度
            user(int):用户的编号
    output: predict(list):推荐列表
    '''
    user_list = data[:,0]
    video_list = data[:][0]
    data = data[1:][1:]

    user = list(user_list).index(user)

    m, n = np.shape(data)
    interaction = data[user,]  # 用户user与商品信息

    # 1、找到用户user没有互动过的商品
    not_inter = []
    for i in range(n):
        if interaction[i] == 0:  # 没有互动的商品
            not_inter.append(i)

    # 2、对没有互动过的商品进行预测
    predict = {}
    for x in not_inter:
        item = np.copy(data[:, x])  # 找到所有用户对商品x的互动信息
        for i in range(m):  # 对每一个用户
            if item[i] != 0:  # 若该用户对商品x有过互动
                if x not in predict:
                    predict[x] = w[user, i] * item[i]
                else:
                    predict[x] = predict[x] + w[user, i] * item[i]
    # 3、按照预测的大小从大到小排序
    return sorted(predict.items(), key=lambda d: d[1], reverse=True)

def top_k(predict, k):
    '''为用户推荐前k个商品
    input:  predict(list):排好序的商品列表
            k(int):推荐的商品个数
    output: top_recom(list):top_k个商品
    '''
    top_recom = []
    len_result = len(predict)
    if k >= len_result:
        top_recom = predict
    else:
        for i in range(k):
            top_recom.append(predict[i])
    return top_recom

if __name__ == '__main__':
    # 1、导入用户商品数据
    print("------------ 1. 加载数据 ------------")
    data = mv100.generateMatrix("./test.csv")
    print("------------ 2. 计算两个用户间的相似度-------------")
    w = similarity(data,777)
    # 3、利用用户之间的相似性进行推荐
    print("------------ 3. 预测用户与没有交互过得产品的喜爱程度 ------------")
    predict = user_based_recommend(data, w, 777)
    # 4、进行Top-K推荐
    print("------------ 4. 推荐k个产品 ------------")
    top_recom = top_k(predict, 2)
    print(top_recom)
