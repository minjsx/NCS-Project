package moroom.VO;

public class PaymentInfo
{
	int piNo;		// ����������ȣ
	int pwNo;		// �������ܹ�ȣ
	int mNo;		// ���͵� ���� ��ȣ
	int scNo;		// ���͵� ���� ��ȣ
	int piMoney;	// �����ݾ�
	String piDate;	// �����Ͻ�
	
	public int getPiNo() 			{return piNo;}
	public void setPiNo(int piNo) 	{this.piNo = piNo;}
	
	public int getPwNo() 			{return pwNo;}
	public void setPwNo(int pwNo) 	{this.pwNo = pwNo;}
	
	public int getmNo() 			{return mNo;}
	public void setmNo(int mNo) 	{this.mNo = mNo;}
	
	public int getScNo() 			{return scNo;}
	public void setScNo(int scNo) 	{this.scNo = scNo;}

	public int getPiMoney() 			{return piMoney;}
	public void setPiMoney(int piMoney) {this.piMoney = piMoney;}
	
	public String getPiDate() 				{return piDate;}
	public void setPiDate(String piDate) 	{this.piDate = piDate;}

	public PaymentInfo() {}
	
	public PaymentInfo(int piNo, int pwNo,int mNo, int scNo, int piMoney, String piDate) 
	{
		this.piNo = piNo;
		this.pwNo = pwNo;
		this.mNo = mNo;
		this.scNo = scNo;
		this.piMoney = piMoney;
		this.piDate = piDate;
	}
	
	
}
