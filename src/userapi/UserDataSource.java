package userapi;

public class UserDataSource implements DataSource {
private final int valueA;
private final int valueB;

public UserDataSource(int valueA, int valueB) {
	super();
	this.valueA = valueA;
	this.valueB = valueB;
}

public int getValueA() {
	return valueA;
}

public int getValueB() {
	return valueB;
}



}
