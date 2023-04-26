import nacos
import threading
from flask import Flask

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


@app.route("/py",methods=["get"])
def py1():
	print("received get request!")
	return "hello, this is the server speaking"
if __name__ == '__main__':
	threading.Timer(5, service_register).start()
	app.run()
