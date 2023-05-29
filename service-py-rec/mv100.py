import numpy as np
import pandas as pd
def generateMatrix(filename):

    #获得numpy array
    csvFile = np.genfromtxt(filename,delimiter=",")
    print(csvFile.shape)


    #去除time stamp和头行信息
    csvFile = csvFile[1:,-2:]
    print(csvFile.shape)


    #获得应该构建的矩阵大小
    pdFrame = pd.DataFrame(csvFile)
    print(pdFrame)
    user_num = pdFrame.value_counts(0).count()
    video_num = pdFrame.value_counts(1).count()

    #先构建空白矩阵
    matrix = np.zeros((user_num+1,video_num+1),dtype=int)
    print(matrix.shape)

    #获得user list和video list, 此时不含重复元素
    userList = csvFile[:,0].tolist()
    userList = list(set(userList))
    videoList = csvFile[:,1].tolist()
    videoList = list(set(videoList))

    #给空白矩阵赋值,此时先把左,上的标签写上
    for i in range(0, len(userList)):
        matrix[i+1][0] = int(userList[i])
    for i in range(0,len(videoList)):
        matrix[0][i+1] = int(videoList[i])

    #给空白矩阵赋值,
    for i in range(0,csvFile.shape[0]):
        user = int(csvFile[i][0])
        video = int(csvFile[i][1])
        user_index = userList.index(user) +1
        video_index = videoList.index(video)+1
        matrix[user_index][video_index] = 1
    print(matrix)
    return matrix
if __name__ == '__main__':
    generateMatrix("./test.csv")