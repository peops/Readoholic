package readoholic;

public class Asset {
	private String assetId;
	private String assetName;
	private String assetClass;
	private String assetOwnerId;
	private String assetBorrowerId;
	private String assetDescription;
	private int assetSecurityDeposit;
	private boolean assetRequestStatus=false;
	private boolean assetAllocationStatus=false;
	
	public Asset(String assetId, String assetName, String assetClass, String assetOwnerId, String assetBorrowerId,
			String assetDescription, int assetSecurityDeposit,
			boolean assetRequestStatus, boolean assetAllocationStatus) {
		this.assetId = assetId;
		this.assetName = assetName;
		this.assetClass = assetClass;
		this.assetDescription = assetDescription;
		this.assetOwnerId = assetOwnerId;
		this.assetBorrowerId = assetBorrowerId;
		this.assetSecurityDeposit = assetSecurityDeposit; 
		this.assetRequestStatus = assetRequestStatus;
		this.assetAllocationStatus = assetAllocationStatus;
	}
	public Asset(String assetName, String assetClass, String assetOwnerId,
			String assetDescription, int assetSecurityDeposit) {
		String[] id = new String[]{assetName, assetClass, assetOwnerId, assetDescription, Integer.toString(assetSecurityDeposit)};
		this.assetId = Hashcode.generate(id);
		this.assetName = assetName;
		this.assetClass = assetClass;
		this.assetDescription = assetDescription;
		this.assetOwnerId = assetOwnerId;
		this.assetSecurityDeposit = assetSecurityDeposit;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getAssetClass() {
		return assetClass;
	}
	public void setAssetClass(String assetClass) {
		this.assetClass = assetClass;
	}
	public String getAssetOwnerId() {
		return assetOwnerId;
	}
	public void setAssetOwnerId(String assetOwnerId) {
		this.assetOwnerId = assetOwnerId;
	}
	public String getAssetBorrowerId() {
		return assetBorrowerId;
	}
	public void setAssetBorrowerId(String assetBorrowerId) {
		this.assetBorrowerId = assetBorrowerId;
	}
	public String getAssetDescription() {
		return assetDescription;
	}
	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}
	public int getAssetSecurityDeposit() {
		return assetSecurityDeposit;
	}
	public void setAssetSecurityDeposit(int assetSecurityDeposit) {
		this.assetSecurityDeposit = assetSecurityDeposit;
	}
	public boolean isAssetRequestStatus() {
		return assetRequestStatus;
	}
	public void setAssetRequestStatus(boolean assetRequestStatus) {
		this.assetRequestStatus = assetRequestStatus;
	}
	public boolean isAssetAllocationStatus() {
		return assetAllocationStatus;
	}
	public void setAssetAllocationStatus(boolean assetAllocationStatus) {
		this.assetAllocationStatus = assetAllocationStatus;
	}

	@Override
	public boolean equals(Object other) {
	    if (!(other instanceof Asset)) {
	        return false;
	    }
	    Asset that = (Asset) other;
	    return (this.assetId == that.assetId) && (this.assetOwnerId == that.assetOwnerId);
	}
	
	@Override
	public String toString() { 
	    return "Product ID: " + assetId + 
	    		"\nName: " + this.assetName + 
	    		"\nClass: " + this.assetClass + 
	    		"\nOwner: " + this.assetOwnerId + 
	    		"\nDescription: " + this.assetDescription + 
	    		"\nSecurity Deposit: " + this.assetSecurityDeposit;
	}
}
