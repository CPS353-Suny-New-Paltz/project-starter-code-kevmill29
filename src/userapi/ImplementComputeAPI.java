package userapi;

public class ImplementComputeAPI implements ComputeAPI{
ComputeResponse run;

public ComputeResponse run() {
	return run;
}

public DefaultComputeResponse runDefault() {
	return runDefault;
}

DefaultComputeResponse runDefault;

public ImplementComputeAPI(ComputeResponse run, DefaultComputeResponse runDefault) {
	super();
	this.run = run;
	this.runDefault = runDefault;
}



@Override
public ComputeResponse run(ComputeRequest request) {
	// TODO Auto-generated method stub
	return null;
}

}
