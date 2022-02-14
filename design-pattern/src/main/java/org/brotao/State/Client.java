package org.brotao.State;

/**
 * @author luotao
 * @className Client
 * @description
 * @date 2022-01-25 14:02
 */
public class Client {
	public static void main(String[] args) {
		GumballMachine gumballMachine = new GumballMachine(5);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		gumballMachine.insertQuarter();
		gumballMachine.ejectQuarter();
		gumballMachine.turnCrank();

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.ejectQuarter();

		gumballMachine.insertQuarter();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
	}
}
