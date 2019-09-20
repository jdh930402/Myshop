package shop.order;

import java.util.Hashtable;

public class CartMgr {
	private Hashtable hCart = new Hashtable<>();

	public void addCart(OrderBean bean) {
		String product_no = bean.getProduct_no();
		int quantity = Integer.parseInt(bean.getQuantity());
		
		if(quantity > 0 ) {

			if(hCart.containsKey(product_no)) { // 상품 번호가 카트에 존재할 경우 수량만 증가.
				OrderBean temp = (OrderBean) hCart.get(product_no);
				quantity += Integer.parseInt(temp.getQuantity());
				temp.setQuantity(Integer.toString(quantity));
				hCart.put(product_no, temp);
				
			} else { // 새 상품 주문시
				hCart.put(product_no, bean);
			}			
		}
	}

	public Hashtable getCartList() {
		return hCart;
	}

	public void updateCart(OrderBean bean) {
		String product_no = bean.getProduct_no();
		hCart.put(product_no, bean);
	}

	public void deleteCart(OrderBean bean) {
		String product_no = bean.getProduct_no();
		hCart.remove(product_no);
	}
}
