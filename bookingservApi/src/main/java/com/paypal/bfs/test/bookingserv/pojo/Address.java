package com.paypal.bfs.test.bookingserv.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @JsonPropertyDescription("Booking id")
    private Integer id;

    @Column
    @JsonProperty("line1")
    @JsonPropertyDescription("line1")
    @Size(min = 1, max = 50)
    @NotBlank(message="Address line 1 can't be empty")
	private String line1;
	
    @Column
    @JsonProperty("line2")
    @JsonPropertyDescription("line2")
    @Size(max = 50)
	private String line2;
	
    @Column
    @JsonProperty("city")
    @JsonPropertyDescription("city")
    @Size(min = 1, max = 50)
    @NotBlank(message="City can't be empty")
	private String city;

    @Column
    @JsonProperty("state")
    @JsonPropertyDescription("state")
    @Size(min = 1, max = 20)
    @NotBlank(message="State can't be empty")
	private String state;

    @Column
    @JsonProperty("zip_code")
    @JsonPropertyDescription("Zip code")
    @Min(100000)
    @Max(999999)
    @NotNull(message="Zipcode can't be empty")
	private Integer zipCode;

    //@OneToOne(mappedBy = "address")
    //private Booking booking;

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}


}
