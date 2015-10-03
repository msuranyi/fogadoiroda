package hu.gdf.oop.fogadoiroda.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("osszeadasService")
public class OsszeadasServiceImpl implements OsszeadasService {

	/**
	 * Logger.
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(OsszeadasServiceImpl.class);

	@Override
	public Integer osszead(Integer... values) {

		// Bemeneti paraméterek vannak-e egyáltalán, és ha igen van-e legalább 2
		if (values == null || values.length < 2) {
			throw new IllegalArgumentException(
				"Az összeadás művelet csak legalább két szám átadása esetén értelmezhető!");
		}

		int result = 0;

		// Iteráció a bemeneti paramétereken
		for (Integer v : values) {

			// Bemeneti paraméterek között van-e null
			if (v == null) {
				throw new IllegalArgumentException("Az összeadás művelet bemeneteként nem adható át null érték!");
			}
			result += v;
		}

		LOGGER.debug("osszead metódus eredménye: {}", result);
		return result;
	}
}
