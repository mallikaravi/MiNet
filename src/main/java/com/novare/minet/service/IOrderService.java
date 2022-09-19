/**
 * 
 */
package com.novare.minet.service;

import java.util.List;

import com.novare.minet.model.Order;

/**
 *
 */
public interface IOrderService extends IMiNetService {

	Order create(Order order) throws Exception;

	Order update(Order order) throws Exception;

	Order delete(Order order) throws Exception;

	List<Order> findAllPending() throws Exception;

	List<Order> findAllWaiting() throws Exception;

	List<Order> search(String keyword) throws Exception;

	List<Order> getAll() throws Exception;

}
