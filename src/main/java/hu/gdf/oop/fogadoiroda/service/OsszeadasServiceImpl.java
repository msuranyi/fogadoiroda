package hu.gdf.oop.fogadoiroda.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("osszeadasService")
public class OsszeadasServiceImpl implements OsszeadasService {

	private final static Logger LOGGER = LoggerFactory.getLogger(OsszeadasServiceImpl.class);

	@Override
	public Integer osszead(Integer... values) {

		LOGGER.debug("osszead metódus hívása");

		if (values == null || values.length < 2) {
			throw new IllegalArgumentException(
				"Az összeadás művelet csak legalább két szám átadása esetén értelmezhető!");
		}
		int result = 0;
		for (Integer v : values) {
			if (v == null) {
				throw new IllegalArgumentException("Az összeadás művelet bemeneteként nem adható át null érték!");
			}
			result += v;
		}
		return result;
	}
}
