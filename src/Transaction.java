
public class Transaction {
	static int id = 0;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + transactionID;
		return result;
	}
	
	public String toString() {
		return "<id, "+ transactionID + "><from, " + sender + "><receiver, " + receiver + ">";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (transactionID != other.transactionID)
			return false;
		return true;
	}

	int transactionID;
	int sender,receiver;

	public Transaction(int sender,int receiver) {
		this.sender = sender;
		this.receiver = receiver;
		this.transactionID = id++;
	}

}
