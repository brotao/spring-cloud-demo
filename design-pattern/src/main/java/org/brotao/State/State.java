package org.brotao.State;

/**
 * @author luotao
 * @className State
 * @description
 * @date 2022-01-25 14:00
 */
public interface State {
	/**
	 * 投入 25 分钱
	 */
	void insertQuarter();

	/**
	 * 退回 25 分钱
	 */
	void ejectQuarter();

	/**
	 * 转动曲柄
	 */
	void turnCrank();

	/**
	 * 发放糖果
	 */
	void dispense();
}
