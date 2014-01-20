package hudson.plugins;


import net.sf.json.JSONObject;
import org.kohsuke.stapler.StaplerRequest;
import hudson.Extension;
import hudson.model.Descriptor;
import hudson.model.Job;
import hudson.views.ListViewColumn;
import hudson.model.Cause;
import hudson.model.Cause.UserIdCause;
import hudson.model.Run;


public class StartedByViewColumn extends ListViewColumn{

    String name ;
	public String getStartedBy(Job job){
        try{
        Run lastBuild = job.getLastBuild();

        Cause.UserIdCause cause = (Cause.UserIdCause) lastBuild.getCause(Cause.UserIdCause.class);
        name = cause.getUserName();
        }
        catch(Exception e){name = "";}
		return "<p>"+name+"</p>";
	}


	 @Extension
    public static final Descriptor<ListViewColumn> DESCRIPTOR = new Descriptor<ListViewColumn>(){
        @Override
        public ListViewColumn newInstance(StaplerRequest req, JSONObject formData){
        	return new StartedByViewColumn();
        }
        
		@Override
		public String getDisplayName(){
			return "Last deployed by";
		}
    };

    public Descriptor<ListViewColumn> getDescriptor(){
        return DESCRIPTOR;
    }

}