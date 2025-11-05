package conceptapi;

public class UserComponent implements UserInterface{

	
	public boolean checkValue(int valueA) {

		return valueA> 1000 || valueA < -1000;
			
	}
}