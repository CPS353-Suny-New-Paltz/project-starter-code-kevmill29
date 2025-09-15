package processapi;
import project.annotations.ProcessAPI;
@ProcessAPI
public interface Processapi {
	DefaultProcessResponse run(RunDataStream stream);
}
