import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public class StockTrade {
	private BinHeap<Bid> sellersQueue;
	private BinHeap<Bid> buyersQueue;
	private HashMap<String,Integer> sellersMap;
	private HashMap<String,Integer> buyersMap;


	public StockTrade() {  
		Comparator<Bid> compSell = new BidComparator(true);
		Comparator<Bid> compBuy = new BidComparator(false);
		sellersQueue = new BinHeap<Bid>(compSell);
		buyersQueue = new BinHeap<Bid>(compBuy);
		sellersMap = new HashMap<String,Integer>();
		buyersMap = new HashMap<String,Integer>();
	}



	public Transaction placeSellBid(Bid bid) {
		// Seller allowed to both raise and lower price
		if (sellersMap.containsKey(bid.name)){
			sellersQueue.remove(new Bid(bid.name, sellersMap.get(bid.name)));
		}
		sellersMap.put(bid.name, bid.price);
		sellersQueue.add(bid);
		return eventualTransaction();
	}

	public Transaction placeBuyBid(Bid bid) {
		// Buyer allowed to both raise and lower price
		if (buyersMap.containsKey(bid.name)){
			buyersQueue.remove(new Bid(bid.name, buyersMap.get(bid.name)));
		}
		buyersMap.put(bid.name, bid.price);
		buyersQueue.add(bid);
		return eventualTransaction();
	}
	
	public Transaction placeBuyBidReal(Bid bid) { 
		// If buyer already has a bid, and new bid > old bid, update to new
		if (buyersMap.containsKey(bid.name)){
			if (buyersMap.get(bid.name)<bid.price){
				buyersQueue.remove(new Bid(bid.name, buyersMap.get(bid.name)));
				buyersQueue.add(bid);
				buyersMap.put(bid.name, bid.price);
			}
		}else{
			buyersQueue.add(bid);
			buyersMap.put(bid.name, bid.price);
		}
		return eventualTransaction(); 
	}

	public Transaction eventualTransaction(){
		// If highest bid(seller) <= lowest price (buyer), make transaction lol 
		if (sellersQueue.peek() != null && buyersQueue.peek() != null){
			if (sellersQueue.peek().price <= buyersQueue.peek().price) {
				Bid seller = sellersQueue.poll();
				Bid buyer = buyersQueue.poll();
				return new Transaction(seller.name, buyer.name, buyer.price);
			}
		}
		return null;
	}

	public Iterator<Bid> sellBidsIterator() {
		return sellersQueue.iterator();
	}

	public Iterator<Bid> buyBidsIterator() {
		return buyersQueue.iterator();
	}
	public static void main(String[] args){
		StockTrade test = new StockTrade();
		Transaction t1 = test.placeBuyBid(new Bid("Namn",50));
		System.out.println(test.buyersQueue.getList());
		t1 = test.placeBuyBid(new Bid("Ada",10));
		System.out.println(test.buyersQueue.getList());
		t1 = test.placeBuyBid(new Bid("Bengt",60));
		System.out.println(test.buyersQueue.getList());
		t1 = test.placeBuyBid(new Bid("Namn",50));
		System.out.println(test.buyersQueue.getList());
		t1 = test.placeBuyBid(new Bid("Namn",40));
		System.out.println(test.buyersQueue.getList());
		t1 = test.placeBuyBid(new Bid("Namn",60));
		System.out.println(test.buyersQueue.getList());
		t1 = test.placeSellBid(new Bid ("Carro", 59));
		System.out.println(t1);
		t1 = test.placeSellBid(new Bid ("Carros syster", 70));
		t1 = test.placeBuyBid(new Bid("Ada",100));
		System.out.println(test.sellersQueue.getList());
		System.out.println(test.buyersQueue.getList());


		
	}


	//from java 8 there is static method Comparator.naturalOrder(), but for java 7 this is needed
	private class BidComparator implements Comparator<Bid> {
		private boolean sell;

		@Override
		public int compare(Bid o1, Bid o2) {
			Integer p1 = o1.price;
			Integer p2 = o2.price;
			return sell ? p1.compareTo(p2) : p2.compareTo(p1);
		}

		public BidComparator(boolean sell){
			this.sell = sell;
		}
	}
}



