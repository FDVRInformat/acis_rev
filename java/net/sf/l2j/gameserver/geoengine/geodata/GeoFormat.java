package net.sf.l2j.gameserver.geoengine.geodata;

/**
 * @author Hasha
 */
public enum GeoFormat
{
	l2j("%d_%d.l2j"),
	L2OFF("%d_%d_conv.dat"),
	L2D("%d_%d.l2d");

	private final String _filename;

	private GeoFormat(String filename)
	{
		_filename = filename;
	}

	public String getFilename()
	{
		return _filename;
	}
}