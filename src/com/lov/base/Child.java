package com.lov.base;

public class Child extends Parent {

//	private Integer id = new Integer(1);
	
	/**
	 *this����ǰ�����super�����丸�����
	 *���캯���У�Ĭ�ϻ���super�������Ը��๹�������е���
	 *Ĭ�������
	 *	ÿ���಻д�����������Զ������޲ι������������д��ι�������ʵ�֣������������޲ι�������
	 *	��ʱҪ�����޲ι���������Ҫ��ʽʵ���޲ι�������
	 *�������У����еĹ���������Ĭ�ϵ�ͨ��super���������ø�����޲ι�����������д��ι�������û����ʽ�޲ι����������߽��޲ι�����
	 *˽�л����������๹�����л����Ҫ�������ȷָ��Ҫ���õĹ���������ͨ��super���ñ������ɵ��õĹ�����
	 *super���ø��࣬Ҫ���Ƿ���Ȩ������
	 */
	public Child() {
		super(1);//�޲ι�������˽�л������ô��ι�����
		System.out.println("Child()");
		System.out.println(id);//ͨ���������ʵ������Ա����
		System.out.println(this.id);
		System.out.println(super.id);
		System.out.println(this.id == super.id);
	}
	
	
	
}
