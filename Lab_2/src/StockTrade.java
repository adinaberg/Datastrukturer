import java.util.Comparator;
import java.util.Iterator;

public class StockTrade {
    private BinHeap<Bid> sellersQueue;
    private PrioQueue<Bid> buyersQueue;

    public StockTrade() {  
    	Comparator<Bid> comp = new BidComparator();
    	sellersQueue = new BinHeap<Bid>(comp);
    	Bid bid1 = new Bid("Bosse",53);
    	sellersQueue.add(bid1);
    	System.out.println(sellersQueue.getList());
    	
    	
    }

    
    
    public Transaction placeSellBid(Bid bid) { return null;    }
    public Transaction placeBuyBid(Bid bid) { return null; }

    public Iterator<Bid> sellBidsIterator() {
        return sellersQueue.iterator();
    }

    public Iterator<Bid> buyBidsIterator() {
        return buyersQueue.iterator();
    }
    public static void main(String[] args){
    	StockTrade test = new StockTrade();
    }
    

    //from java 8 there is static method Comparator.naturalOrder(), but for java 7 this is needed
    private class BidComparator implements Comparator<Bid> {
    	@Override
    	public int compare(Bid o1, Bid o2) {
    		Integer p1 = o1.price;
    		Integer p2 = o2.price;
    		return p1.compareTo(p2);
    	}



    }
}



