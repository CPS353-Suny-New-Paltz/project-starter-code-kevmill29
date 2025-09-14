package processapi;
import project.annotations.ProcessAPI;
@ProcessAPI
public interface Processapi {
	ProcessResponse run(RunDataStream stream);
}
