һ���ڵ���
1.�ֽ���
	1��������	InputStream FileInputStream ByteArrayInputStream
		read�ֽ�����
		�м�����	byte[] flush = new byte[length]
		���ܳ���	int len = 0
		ѭ����ȡ	while(-1 != (len = is.read(flush))){}
	2�������	OutputStream FileOutputStream ByteArrayOutputStream
		write(flush,0,len)
		
2.�ַ���
	1��������	Reader FileReader
		read�ַ�����
		�м�����	char[] flush = new char[length]
		���ܳ���	int len = 0
		ѭ����ȡ	while(-1 != (len = is.read(flush))){}
	2�������	Writer FileWriter
		write(flush,0,len)

����������
1��ת����������������ַ�������
	1��������	InputStreamReader -> ����
	2�������	OutputStreamWriter -> ����
2.���������������
	1��������	BufferedInputStream BufferedReader
	2������� 	BufferedOutputStream BufferedWriter
3���������������ͣ�
	�����������ַ�����������ڲ��ܶ�ȡ����ȡ��д��˳��һ��
		1��������	DataInputStream
		2�������	DataOutputStream
	�������ͣ�Serializable�����л����ࣩ transient�������л������ԣ�
		1�������л�	ObjectInputStream - readObject
		2�����л�	ObjectOutputStream - wirteObject
4.��ӡ����PrintStream
5.System.in/out /err /setIn/ setOut