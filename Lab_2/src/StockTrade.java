import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public class StockTrade {
	//Queue of sellers, in prio order
	private PrioQueue<Bid> sellersQueue;
	//Queue of buyers, in prio order
	private PrioQueue<Bid> buyersQueue;
	//Name and bid of sellers
	private HashMap<String,Integer> sellersMap;
	//Name and bid of buyers
	private HashMap<String,Integer> buyersMap;


	//Constructor creating empty queues and maps
	public StockTrade() {  
		Comparator<Bid> compSell = new BidComparator(true);
		Comparator<Bid> compBuy = new BidComparator(false);
		sellersQueue = new BinHeap<Bid>(compSell);
		buyersQueue = new BinHeap<Bid>(compBuy);
		sellersMap = new HashMap<String,Integer>();
		buyersMap = new HashMap<String,Integer>();
	}


	//Places a seller's bid and makes transaction if possible
	public Transaction placeSellBid(Bid bid) {
		// Seller allowed to both raise and lower price
		if (sellersMap.containsKey(bid.name)){
			//Remove previous bid
			sellersQueue.remove(new Bid(bid.name, sellersMap.get(bid.name)));
		}
		//Put new bid in map
		sellersMap.put(bid.name, bid.price);
		//Put new bid in queue
		sellersQueue.add(bid);
		//Make transaction if possible
		return eventualTransaction();
	}

	
	public Transaction placeBuyBid(Bid bid) {
		// Buyer allowed to both raise and lower price
		if (buyersMap.containsKey(bid.name)){
			//Remove previous bid
			buyersQueue.remove(new Bid(bid.name, buyersMap.get(bid.name)));
		}
		//Put new bid in map
		buyersMap.put(bid.name, bid.price);
		//Put new bid in queue
		buyersQueue.add(bid);
		//Make transaction if possible
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
	
	//from java 8 there is static method Comparator.naturalOrder(), but for java 7 this is needed
	//Compares the prices of bids
	private class BidComparator implements Comparator<Bid> {
		// true if comparing sellers, false if comparing buyers
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



