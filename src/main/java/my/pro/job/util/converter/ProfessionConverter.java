package my.pro.job.util.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import my.pro.job.enumeration.Profession;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
@Converter(autoApply = true)
public class ProfessionConverter implements AttributeConverter<Profession, String>{

	@Override
	public String convertToDatabaseColumn(Profession attribute) {
		return attribute==null ? null : attribute.getProfession();
	}

	@Override
	public Profession convertToEntityAttribute(String dbData) {
		return Stream.of(Profession.values())
				.filter(p -> p.getProfession().equals(dbData))
				.findFirst()
				.orElseThrow(()->
					new IllegalArgumentException(dbData+" does not existe"));
	}

}
