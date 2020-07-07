package my.pro.job.util.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import my.pro.job.enumeration.Diploma;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
@Converter(autoApply = true)
public class DiplomaConverter implements AttributeConverter<Diploma, String>{

	@Override
	public String convertToDatabaseColumn(Diploma attribute) {
		return attribute==null? null : attribute.getDegree();
	}

	@Override
	public Diploma convertToEntityAttribute(String dbData) {
		return Stream.of(Diploma.values())
			.filter(d -> d.getDegree().equals(dbData))
				.findFirst()
				.orElseThrow(
					()->new IllegalArgumentException(dbData+" does not existe")
						);
	}

	
}
