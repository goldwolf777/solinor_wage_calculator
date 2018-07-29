package co.uk.solinor.wagecalculator.SalaryCalculation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties
@PropertySource(value = {"classpath:webserver.properties", "file:/etc/solinor/webserver.properties"}, ignoreResourceNotFound = true)
public class SalaryProperties {

	@Value("${salary.standard.rate:4.25}")
	private Double standardRate;

	@Value("${salary.extrapay.bonus.rate:1.25}")
	private Double extraPayBonus;

	@Value("${salary.overtime1.rate:25}")
	private int overtimeRate1;

	@Value("${salary.overtime2.rate:50}")
	private int overtimeRate2;

	@Value("${salary.overtime3.rate:100}")
	private int overtimeRate3;

	public Double getStandardRate() {
		return standardRate;
	}

	public void setStandardRate(Double standardRate) {
		this.standardRate = standardRate;
	}

	public Double getExtraPayBonus() {
		return extraPayBonus;
	}

	public void setExtraPayBonus(Double extraPayBonus) {
		this.extraPayBonus = extraPayBonus;
	}

	public int getOvertimeRate1() {
		return overtimeRate1;
	}

	public void setOvertimeRate1(int overtimeRate1) {
		this.overtimeRate1 = overtimeRate1;
	}

	public int getOvertimeRate2() {
		return overtimeRate2;
	}

	public void setOvertimeRate2(int overtimeRate2) {
		this.overtimeRate2 = overtimeRate2;
	}

	public int getOvertimeRate3() {
		return overtimeRate3;
	}

	public void setOvertimeRate3(int overtimeRate3) {
		this.overtimeRate3 = overtimeRate3;
	}
}
