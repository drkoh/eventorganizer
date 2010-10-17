package Event;

import Actor.BgProcesser;

public interface Activicable {
	int trigger(BgProcesser bgprocesser);
	
	//Note:
	// all functions should be returned an integer to 
	// back with an error code of status
}
