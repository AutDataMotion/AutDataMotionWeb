
package datamotion.mvc.mdlcomm;

/**
 * @author lyf
Nov 15, 2016
 *
 */
public interface InfMdlCom {
	public <T> T getKey_();
	public <T> T getPathsrc();
	public <T> T getNamesrc();
	public <T> T getPathdest();
	public <T> T getTimedo();
	public <T> T getFilesize();
	public <T> T getStation();
	public <T> T getAircraft();
	public <T> T getSensor();
	public <T> T getDatatype();
	public <T> T getDatalevel();
	public <T> T getCamera();
	public <T> T getTimerecive();
	public <T> T getTimecollectstart();
	public <T> T getTimecollectend();
	public <T> T getSuffix();
	public <T> T getStatus_();
	public <T> T getTimeadd();
	
	public <T> T getId();
	public <T> T getLabelids();
	
	public <T> T get(String attr);
}
