package com.lov.base;

public abstract class AbstractTest extends Parent {

	public AbstractTest(Integer id) {
		super(id);
		System.out.println("抽象类可继承实体类，但前提是实体类必须有明确的构造函数。任何抽象类都是实际类Object的子类");
	}

	
}
