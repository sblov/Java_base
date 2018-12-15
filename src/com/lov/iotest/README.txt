一、节点流
1.字节流
	1）输入流	InputStream FileInputStream ByteArrayInputStream
		read字节数组
		中间容器	byte[] flush = new byte[length]
		接受长度	int len = 0
		循环读取	while(-1 != (len = is.read(flush))){}
	2）输出流	OutputStream FileOutputStream ByteArrayOutputStream
		write(flush,0,len)
		
2.字符流
	1）输入流	Reader FileReader
		read字符数组
		中间容器	char[] flush = new char[length]
		接受长度	int len = 0
		循环读取	while(-1 != (len = is.read(flush))){}
	2）输出流	Writer FileWriter
		write(flush,0,len)

二、处理流
1。转换流：解码与编码字符集问题
	1）输入流	InputStreamReader -> 解码
	2）输出流	OutputStreamWriter -> 编码
2.缓存流：提高性能
	1）输入流	BufferedInputStream BufferedReader
	2）输出流 	BufferedOutputStream BufferedWriter
3。处理数据与类型：
	基本数据与字符串：必须存在才能读取，读取与写出顺序一致
		1）输入流	DataInputStream
		2）输出流	DataOutputStream
	引用类型：Serializable（序列化的类） transient（不序列化的属性）
		1）反序列化	ObjectInputStream - readObject
		2）序列化	ObjectOutputStream - wirteObject
4.打印流：PrintStream
5.System.in/out /err /setIn/ setOut