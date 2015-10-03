package hu.gdf.oop.fogadoiroda.service;

/**
 * Az összeadás matematikai műveletet elvégző üzlezi komponens publikus
 * interfésze.
 */
public interface OsszeadasService {

	/**
	 * Az összeadás matematikai műveletet elvégző metódus.
	 * 
	 * @param values
	 *            az összeadandó számok.
	 * @return az összeadásra átadott számok összege.
	 */
	Integer osszead(Integer... values);
}
