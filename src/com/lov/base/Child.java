package com.lov.base;

public class Child extends Parent {

//	private Integer id = new Integer(1);
	
	/**
	 *this代表当前类对象，super代表其父类对象
	 *构造函数中，默认会有super（）来对父类构造器进行调用
	 *默认情况：
	 *	每个类不写构造器，会自动生成无参构造器，但若有带参构造器的实现，将不再生成无参构造器，
	 *	此时要调用无参构造器必须要显式实现无参构造器。
	 *在子类中，所有的构造器都会默认的通过super（）来调用父类的无参构造器，如果有带参构造器而没有显式无参构造器，或者将无参构造器
	 *私有化，则在子类构造器中会出错，要求必须明确指定要调用的构造器。可通过super调用被声明可调用的构造器
	 *super调用父类，要考虑访问权限问题
	 */
	public Child() {
		super(1);//无参构造器被私有化，调用带参构造器
		System.out.println("Child()");
		System.out.println(id);//通过基础访问到父类成员变量
		System.out.println(this.id);
		System.out.println(super.id);
		System.out.println(this.id == super.id);
	}
	
	
	
}
