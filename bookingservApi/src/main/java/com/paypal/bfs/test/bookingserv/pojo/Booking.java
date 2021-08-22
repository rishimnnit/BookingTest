
package com.paypal.bfs.test.bookingserv.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import javax.persistence.CascadeType;
import javax.persistence.Column;  
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Booking resource
 * <p>
 * Booking resource object
 * 
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Booking {

    /**
     * Booking id
     * 
     */
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @JsonPropertyDescription("Booking id")
    private Integer id;

	@Column(unique=true, nullable = false)
    @JsonProperty("request_id")
    @JsonPropertyDescription("Request id")
    private String requestId;
    /**
     * First name
     * (Required)
     * 
     */
    @Column
    @JsonProperty("first_name")
    @JsonPropertyDescription("First name")
    @NotEmpty(message="First name can not be empty")
    private String firstName;
    /**
     * Last name
     * (Required)
     * 
     */
    @Column
    @JsonProperty("last_name")
    @JsonPropertyDescription("Last name")
    @NotEmpty(message="Last name can not be empty")
    private String lastName;

    @Column
    @JsonProperty("date_of_birth")
    @JsonPropertyDescription("Date of birth")
    @JsonFormat (pattern = "Yyyy-mm-dd")
    @NotNull(message="Date of birth can not be empty")
    private Date dob;

    @Column
    @JsonProperty("total_price")
    @JsonPropertyDescription("Total Price")
    @NotNull(message="Total price can not be empty")
    private Integer totalPrice;

    @Column
    @JsonProperty("deposit")
    @JsonPropertyDescription("Deposit")
    @NotNull(message="Deposit can not be empty")
    private Integer deposit;

    @Column
    @JsonProperty("checkin_datetime")
    @JsonPropertyDescription("checkin_datetime")
    @JsonFormat (pattern = "Yyyy-mm-dd HH:mm:ss")
    @NotNull(message="Checkin time can not be empty")
    private Date checkinDatetime;

    @Column
    @JsonProperty("checkout_datetime")
    @JsonPropertyDescription("checkout_datetime")
    @JsonFormat (pattern = "Yyyy-mm-dd HH:mm:ss")
    @NotNull(message="Checkout time can not be empty")
    private Date checkoutDatetime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @NotNull(message="Address can not be null")
    @Valid private Address address;

    /**
     * Booking id
     * 
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * Booking id
     * 
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * First name
     * (Required)
     * 
     */
    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    /**
     * First name
     * (Required)
     * 
     */
    @JsonProperty("first_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Last name
     * (Required)
     * 
     */
    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getCheckinDatetime() {
		return checkinDatetime;
	}

	public void setCheckinDatetime(Date checkinDatetime) {
		this.checkinDatetime = checkinDatetime;
	}

	public Date getCheckoutDatetime() {
		return checkoutDatetime;
	}

	public void setCheckoutDatetime(Date checkoutDatetime) {
		this.checkoutDatetime = checkoutDatetime;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getDeposit() {
		return deposit;
	}

	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}

}
