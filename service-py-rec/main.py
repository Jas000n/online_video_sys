import nacos
import threading
from flask import Flask,jsonify

import mv100
from generateREC import similarity, user_based_recommend, top_k

app = Flask(__name__)

# nacos 服务地址
SERVER_ADDRESSES = "http://127.0.0.1:8848"

# 官方文档中更加详细的描述了此方法的形参，包括命名空间、sk、ak等
# 在此处，我只需要传 服务地址 即可
client = nacos.NacosClient(SERVER_ADDRESSES)

def service_register():
	"""
	 ephemeral参数：是否是临时服务，应为false;
	 刚才上面也提到了，如果是 非临时实例，客户端就无需主动完成心跳检测。
	 因此此处将服务注册为 非临时实例
	"""
	client.add_naming_instance("service-py-rec", "127.0.0.1", "5000", ephemeral=False)


@app.route("/py_rec/<id>/<k>",methods=["get"])
def rec(id,k):
	id = int(id)
	k = int(k)
	print("user {} needs {} recommendations".format(id,k))


	# 1、导入用户商品数据
	print("------------ 1. 加载数据 ------------")
	data = mv100.generateMatrix("../cili_parent/service/service_rec/src/main/java/com/Jason/recservice/csvFile/test.csv")
	print("------------ 2. 计算两个用户间的相似度-------------")
	w = similarity(data,id)
	# 3、利用用户之间的相似性进行推荐
	print("------------ 3. 预测用户与没有交互过得产品的喜爱程度 ------------")
	predict = user_based_recommend(data, w, id)
	# 4、进行Top-K推荐
	print("------------ 4. 推荐k个产品 ------------")
	top_recom = top_k(predict, k)
	print(top_recom)
	# 5、这里需要把video index和video id 进行一个转换
	video_list = data[0,:]
	# 6、返回结果
	result =[]
	for i in top_recom:
		result.append(video_list[i[0]])
	# 封装进字典
	result_map ={}
	result_map["recommendations"] = str(result)
	return jsonify(result_map)
if __name__ == '__main__':
	threading.Timer(5, service_register).start()
	app.run()
