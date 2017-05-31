/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.gst.portfolio.client.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import com.gst.infrastructure.codes.domain.CodeValue;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.ApiParameterError;
import com.gst.infrastructure.core.data.DataValidatorBuilder;
import com.gst.infrastructure.core.domain.AbstractPersistableCustom;
import com.gst.infrastructure.core.exception.PlatformApiDataValidationException;
import com.gst.infrastructure.core.service.DateUtils;
import com.gst.infrastructure.documentmanagement.domain.Image;
import com.gst.infrastructure.security.service.RandomPasswordGenerator;
import com.gst.organisation.office.domain.Office;
import com.gst.organisation.staff.domain.Staff;
import com.gst.portfolio.client.api.ClientApiConstants;
import com.gst.portfolio.group.domain.Group;
import com.gst.useradministration.domain.AppUser;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;

@Entity
@Table(name = "m_client", uniqueConstraints = { @UniqueConstraint(columnNames = { "account_no" }, name = "account_no_UNIQUE"), //
        @UniqueConstraint(columnNames = { "mobile_no" }, name = "mobile_no_UNIQUE") })
public final class Client extends AbstractPersistableCustom<Long> {

    @Column(name = "account_no", length = 20, unique = true, nullable = false)
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;

    @ManyToOne
    @JoinColumn(name = "transfer_to_office_id", nullable = true)
    private Office transferToOffice;

    @OneToOne(optional = true)
    @JoinColumn(name = "image_id", nullable = true)
    private Image image;

    /**
     * A value from {@link ClientStatus}.
     */
    @Column(name = "status_enum", nullable = false)
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_status", nullable = true)
    private CodeValue subStatus;
    
    @Column(name = "activation_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date activationDate;

    @Column(name = "office_joining_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date officeJoiningDate;

    @Column(name = "firstname", length = 50, nullable = true)
    private String firstname;

    @Column(name = "middlename", length = 50, nullable = true)
    private String middlename;

    @Column(name = "lastname", length = 50, nullable = true)
    private String lastname;

    @Column(name = "fullname", length = 100, nullable = true)
    private String fullname;

    @Column(name = "display_name", length = 100, nullable = false)
    private String displayName;

    @Column(name = "mobile_no", length = 50, nullable = false, unique = true)
    private String mobileNo;

    @Column(name = "external_id", length = 100, nullable = true, unique = true)
    private String externalId;

    @Column(name = "date_of_birth", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_cv_id", nullable = true)
    private CodeValue gender;

    @Column(name = "company_name", length = 50, nullable = false)
    private String companyName;
    
    @Column(name = "contact_name", length = 50, nullable = false)
    private String contactName;
    
    @Column(name = "office_phone", length = 15, nullable = false)
    private Long officePhone;
    
    @Column(name = "home_phone", length = 15, nullable = false)
    private Long homePhone;
    
    @Column(name = "mobile", length = 10, nullable = false)
    private Long mobile;
    
    @Column(name = "fax", length = 30, nullable = false)
    private String fax;
    
    @Column(name = "email", length = 50, nullable = false)
    private String email;
    
    @Column(name = "gstn_reg_no", length = 20, nullable = false)
    private String gstnRegNo;
    
    @Column(name = "pan_no", length = 20, nullable = false)
    private String panNo;
    
    @Column(name = "etin", length = 10, nullable = false)
    private String etin;
    
    @Column(name = "address_line1", length = 50, nullable = false)
    private String addressLine1;
    
    @Column(name = "address_line2", length = 50, nullable = false)
    private String addressLine2;
    
    @Column(name = "city", length = 20, nullable = false)
    private String city;
    
    @Column(name = "state", length = 20, nullable = false)
    private String state;
    
    @Column(name = "country", length = 20, nullable = false)
    private String country;
    
    @Column(name = "pin", length = 20, nullable = false)
    private Long pin;
    
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "m_group_client", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups;

    @Transient
    private boolean accountNumberRequiresAutoGeneration = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "closure_reason_cv_id", nullable = true)
    private CodeValue closureReason;

    @Column(name = "closedon_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date closureDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reject_reason_cv_id", nullable = true)
    private CodeValue rejectionReason;

    @Column(name = "rejectedon_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date rejectionDate;

    @ManyToOne(optional = true, fetch=FetchType.LAZY)
    @JoinColumn(name = "rejectedon_userid", nullable = true)
    private AppUser rejectedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "withdraw_reason_cv_id", nullable = true)
    private CodeValue withdrawalReason;

    @Column(name = "withdrawn_on_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date withdrawalDate;

    @ManyToOne(optional = true, fetch=FetchType.LAZY)
    @JoinColumn(name = "withdraw_on_userid", nullable = true)
    private AppUser withdrawnBy;

    @Column(name = "reactivated_on_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date reactivateDate;

    @ManyToOne(optional = true, fetch=FetchType.LAZY)
    @JoinColumn(name = "reactivated_on_userid", nullable = true)
    private AppUser reactivatedBy;

    @ManyToOne(optional = true, fetch=FetchType.LAZY)
    @JoinColumn(name = "closedon_userid", nullable = true)
    private AppUser closedBy;

    @Column(name = "submittedon_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date submittedOnDate;

    @ManyToOne(optional = true, fetch=FetchType.LAZY)
    @JoinColumn(name = "submittedon_userid", nullable = true)
    private AppUser submittedBy;

    @Column(name = "updated_on", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date updatedOnDate;

    @ManyToOne(optional = true, fetch=FetchType.LAZY)
    @JoinColumn(name = "updated_by", nullable = true)
    private AppUser updatedBy;

    @ManyToOne(optional = true, fetch=FetchType.LAZY)
    @JoinColumn(name = "activatedon_userid", nullable = true)
    private AppUser activatedBy;

    @Column(name = "default_savings_product", nullable = true)
    private Long savingsProductId;
    
    @Column(name = "default_savings_account", nullable = true)
    private Long savingsAccountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_type_cv_id", nullable = true)
    private CodeValue clientType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_classification_cv_id", nullable = true)
    private CodeValue clientClassification;
    
    @Column(name = "legal_form_enum", nullable = true)
    private Integer legalForm;

    @Column(name = "reopened_on_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date reopenedDate;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "reopened_by_userid", nullable = true)
    private AppUser reopenedBy;

    /*{companyName,contactName,officePhone,
        homePhone, mobile,fax,email,gstnRegNo,panNo,etin,addressLine1,addressLine2,city,state,country,pin}*/
    
    public static Client createNew(final AppUser currentUser, final Office clientOffice, final Group clientParentGroup, final Staff staff,
            final Long savingsProductId, final CodeValue gender, final CodeValue clientType, final CodeValue clientClassification,
            final Integer legalForm, final JsonCommand command) {

        final String accountNo = command.stringValueOfParameterNamed(ClientApiConstants.accountNoParamName);
        final String externalId = command.stringValueOfParameterNamed(ClientApiConstants.externalIdParamName);
        final String mobileNo = command.stringValueOfParameterNamed(ClientApiConstants.mobileNoParamName);

        final String firstname = command.stringValueOfParameterNamed(ClientApiConstants.firstnameParamName);
        final String middlename = command.stringValueOfParameterNamed(ClientApiConstants.middlenameParamName);
        final String lastname = command.stringValueOfParameterNamed(ClientApiConstants.lastnameParamName);
        final String fullname = command.stringValueOfParameterNamed(ClientApiConstants.fullnameParamName);
        		
        final String companyName = command.stringValueOfParameterNamed(ClientApiConstants.companyNameParamName);
        final String contactName = command.stringValueOfParameterNamed(ClientApiConstants.contactNameParamName);
        final Long officePhone = command.longValueOfParameterNamed(ClientApiConstants.officePhoneParamName);
        final Long homePhone = command.longValueOfParameterNamed(ClientApiConstants.homePhoneParamName);
        final Long mobile = command.longValueOfParameterNamed(ClientApiConstants.mobileParamName);
        final String fax = command.stringValueOfParameterNamed(ClientApiConstants.faxParamName);
        final String email = command.stringValueOfParameterNamed(ClientApiConstants.emailParamName);
        final String gstnRegNo = command.stringValueOfParameterNamed(ClientApiConstants.gstnRegNoParamName);
        final String panNo = command.stringValueOfParameterNamed(ClientApiConstants.panNoParamName);
        final String etin = command.stringValueOfParameterNamed(ClientApiConstants.etinParamName);
        final String addressLine1 = command.stringValueOfParameterNamed(ClientApiConstants.addressLine1ParamName);
        final String addressLine2 = command.stringValueOfParameterNamed(ClientApiConstants.addressLine2ParamName);
        final String city = command.stringValueOfParameterNamed(ClientApiConstants.cityParamName);
        final String state = command.stringValueOfParameterNamed(ClientApiConstants.stateParamName);
        final String country = command.stringValueOfParameterNamed(ClientApiConstants.countryParamName);
        final Long pin = command.longValueOfParameterNamed(ClientApiConstants.pinParamName);

        final LocalDate dataOfBirth = command.localDateValueOfParameterNamed(ClientApiConstants.dateOfBirthParamName);

        ClientStatus status = ClientStatus.PENDING;
        boolean active = false;
        if (command.hasParameter("active")) {
            active = command.booleanPrimitiveValueOfParameterNamed(ClientApiConstants.activeParamName);
        }

        LocalDate activationDate = null;
        LocalDate officeJoiningDate = null;
        if (active) {
            status = ClientStatus.ACTIVE;
            activationDate = command.localDateValueOfParameterNamed(ClientApiConstants.activationDateParamName);
            officeJoiningDate = activationDate;
        }

        LocalDate submittedOnDate = new LocalDate();
        if (active && submittedOnDate.isAfter(activationDate)) {
            submittedOnDate = activationDate;
        }
        if (command.hasParameter(ClientApiConstants.submittedOnDateParamName)) {
            submittedOnDate = command.localDateValueOfParameterNamed(ClientApiConstants.submittedOnDateParamName);
        }
        final Long savingsAccountId = null;
        return new Client(currentUser, status, clientOffice, clientParentGroup, accountNo, firstname, middlename, lastname, fullname,
                activationDate, officeJoiningDate, externalId, mobileNo, staff, submittedOnDate, savingsProductId, savingsAccountId, dataOfBirth,
                gender, clientType, clientClassification, legalForm,companyName,contactName,officePhone,
                homePhone, mobile,fax,email,gstnRegNo,panNo,etin,addressLine1,addressLine2,city,state,country,pin);
    }

    protected Client() {
    	this.setLegalForm(null);
    }

    private Client(final AppUser currentUser, final ClientStatus status, final Office office, final Group clientParentGroup,
            final String accountNo, final String firstname, final String middlename, final String lastname, final String fullname,
            final LocalDate activationDate, final LocalDate officeJoiningDate, final String externalId, final String mobileNo,
            final Staff staff, final LocalDate submittedOnDate, final Long savingsProductId, final Long savingsAccountId,
            final LocalDate dateOfBirth, final CodeValue gender, final CodeValue clientType, final CodeValue clientClassification, final Integer legalForm,
            final String companyName,final String contactName,final Long officePhone,final Long homePhone,final Long mobile,final String fax,final String email,
            final String gstnRegNo,final String panNo,final String etin,final String addressLine1,final String addressLine2,final String city,final String state,final String country,final Long pin) {

        if (StringUtils.isBlank(accountNo)) {
            this.accountNumber = new RandomPasswordGenerator(19).generate();
            this.accountNumberRequiresAutoGeneration = true;
        } else {
            this.accountNumber = accountNo;
        }

        this.submittedOnDate = submittedOnDate.toDate();
        this.submittedBy = currentUser;

        this.status = status.getValue();
        this.office = office;
        if (StringUtils.isNotBlank(externalId)) {
            this.externalId = externalId.trim();
        } else {
            this.externalId = null;
        }

        if (StringUtils.isNotBlank(mobileNo)) {
            this.mobileNo = mobileNo.trim();
        } else {
            this.mobileNo = null;
        }

        if (activationDate != null) {
            this.activationDate = activationDate.toDateTimeAtStartOfDay().toDate();
            this.activatedBy = currentUser;
        }
        if (officeJoiningDate != null) {
            this.officeJoiningDate = officeJoiningDate.toDateTimeAtStartOfDay().toDate();
        }
        if (StringUtils.isNotBlank(firstname)) {
            this.firstname = firstname.trim();
        } else {
            this.firstname = null;
        }

        if (StringUtils.isNotBlank(middlename)) {
            this.middlename = middlename.trim();
        } else {
            this.middlename = null;
        }

        if (StringUtils.isNotBlank(lastname)) {
            this.lastname = lastname.trim();
        } else {
            this.lastname = null;
        }

        if (StringUtils.isNotBlank(fullname)) {
            this.fullname = fullname.trim();
        } else {
            this.fullname = null;
        }

        if (clientParentGroup != null) {
            this.groups = new HashSet<>();
            this.groups.add(clientParentGroup);
        }

        this.staff = staff;
        this.savingsProductId = savingsProductId;
        this.savingsAccountId = savingsAccountId;

        if (gender != null) {
            this.gender = gender;
        }
        if (dateOfBirth != null) {
            this.dateOfBirth = dateOfBirth.toDateTimeAtStartOfDay().toDate();
        }
        this.clientType = clientType;
        this.clientClassification = clientClassification;
        this.setLegalForm(legalForm);
        
        this.companyName = companyName;
        this.contactName = contactName;
        this.officePhone = officePhone;
        this.homePhone = homePhone;
        this.mobile = mobile;
        this.fax = fax;
        this.email = email;
        this.gstnRegNo = gstnRegNo;
        this.panNo = panNo;
        this.etin = etin;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pin = pin;

        deriveDisplayName();
        validate();
    }

    private void validate() {
        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
        validateNameParts(dataValidationErrors);
        validateActivationDate(dataValidationErrors);

        if (!dataValidationErrors.isEmpty()) { throw new PlatformApiDataValidationException(dataValidationErrors); }

    }
    
    private void validateUpdate() {
        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
        //Not validating name parts while update request as firstname/lastname can be along with fullname 
        //when we change clientType from Individual to Organisation or vice-cersa
        validateActivationDate(dataValidationErrors);

        if (!dataValidationErrors.isEmpty()) { throw new PlatformApiDataValidationException(dataValidationErrors); }

    }

    public boolean isAccountNumberRequiresAutoGeneration() {
        return this.accountNumberRequiresAutoGeneration;
    }

    public void setAccountNumberRequiresAutoGeneration(final boolean accountNumberRequiresAutoGeneration) {
        this.accountNumberRequiresAutoGeneration = accountNumberRequiresAutoGeneration;
    }

    public boolean identifiedBy(final String identifier) {
        return identifier.equalsIgnoreCase(this.externalId);
    }

    public boolean identifiedBy(final Long clientId) {
        return getId().equals(clientId);
    }

    public void updateAccountNo(final String accountIdentifier) {
        this.accountNumber = accountIdentifier;
        this.accountNumberRequiresAutoGeneration = false;
    }

    public void activate(final AppUser currentUser, final DateTimeFormatter formatter, final LocalDate activationLocalDate) {

        if (isActive()) {
            final String defaultUserMessage = "Cannot activate client. Client is already active.";
            final ApiParameterError error = ApiParameterError.parameterError("error.msg.clients.already.active", defaultUserMessage,
                    ClientApiConstants.activationDateParamName, activationLocalDate.toString(formatter));

            final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
            dataValidationErrors.add(error);

            throw new PlatformApiDataValidationException(dataValidationErrors);
        }

        this.activationDate = activationLocalDate.toDate();
        this.activatedBy = currentUser;
        this.officeJoiningDate = this.activationDate;
        this.status = ClientStatus.ACTIVE.getValue();
        
        // in case a closed client is being re open
        this.closureDate = null;
        this.closureReason = null;
        this.closedBy = null;

        validate();
    }

    public boolean isNotActive() {
        return !isActive();
    }

    public boolean isActive() {
        return ClientStatus.fromInt(this.status).isActive();
    }

    public boolean isClosed() {
        return ClientStatus.fromInt(this.status).isClosed();
    }

    public boolean isTransferInProgress() {
        return ClientStatus.fromInt(this.status).isTransferInProgress();
    }

    public boolean isTransferOnHold() {
        return ClientStatus.fromInt(this.status).isTransferOnHold();
    }

    public boolean isTransferInProgressOrOnHold() {
        return isTransferInProgress() || isTransferOnHold();
    }

    public boolean isNotPending() {
        return !isPending();
    }

    public boolean isPending() {
        return ClientStatus.fromInt(this.status).isPending();
    }

    private boolean isDateInTheFuture(final LocalDate localDate) {
        return localDate.isAfter(DateUtils.getLocalDateOfTenant());
    }
    
    public boolean isRejected() {
        return ClientStatus.fromInt(this.status).isRejected();
    }
    
    public boolean isWithdrawn() {
        return ClientStatus.fromInt(this.status).isWithdrawn();
    }
    
    public Map<String, Object> update(final JsonCommand command) {

        final Map<String, Object> actualChanges = new LinkedHashMap<>(9);

        if (command.isChangeInIntegerParameterNamed(ClientApiConstants.statusParamName, this.status)) {
            final Integer newValue = command.integerValueOfParameterNamed(ClientApiConstants.statusParamName);
            actualChanges.put(ClientApiConstants.statusParamName, ClientEnumerations.status(newValue));
            this.status = ClientStatus.fromInt(newValue).getValue();
        }

        if (command.isChangeInStringParameterNamed(ClientApiConstants.accountNoParamName, this.accountNumber)) {
            final String newValue = command.stringValueOfParameterNamed(ClientApiConstants.accountNoParamName);
            actualChanges.put(ClientApiConstants.accountNoParamName, newValue);
            this.accountNumber = StringUtils.defaultIfEmpty(newValue, null);
        }

        if (command.isChangeInStringParameterNamed(ClientApiConstants.externalIdParamName, this.externalId)) {
            final String newValue = command.stringValueOfParameterNamed(ClientApiConstants.externalIdParamName);
            actualChanges.put(ClientApiConstants.externalIdParamName, newValue);
            this.externalId = StringUtils.defaultIfEmpty(newValue, null);
        }

        if (command.isChangeInStringParameterNamed(ClientApiConstants.mobileNoParamName, this.mobileNo)) {
            final String newValue = command.stringValueOfParameterNamed(ClientApiConstants.mobileNoParamName);
            actualChanges.put(ClientApiConstants.mobileNoParamName, newValue);
            this.mobileNo = StringUtils.defaultIfEmpty(newValue, null);
        }

        if (command.isChangeInStringParameterNamed(ClientApiConstants.firstnameParamName, this.firstname)) {
            final String newValue = command.stringValueOfParameterNamed(ClientApiConstants.firstnameParamName);
            actualChanges.put(ClientApiConstants.firstnameParamName, newValue);
            this.firstname = StringUtils.defaultIfEmpty(newValue, null);
        }

        if (command.isChangeInStringParameterNamed(ClientApiConstants.middlenameParamName, this.middlename)) {
            final String newValue = command.stringValueOfParameterNamed(ClientApiConstants.middlenameParamName);
            actualChanges.put(ClientApiConstants.middlenameParamName, newValue);
            this.middlename = StringUtils.defaultIfEmpty(newValue, null);
        }

        if (command.isChangeInStringParameterNamed(ClientApiConstants.lastnameParamName, this.lastname)) {
            final String newValue = command.stringValueOfParameterNamed(ClientApiConstants.lastnameParamName);
            actualChanges.put(ClientApiConstants.lastnameParamName, newValue);
            this.lastname = StringUtils.defaultIfEmpty(newValue, null);
        }

        if (command.isChangeInStringParameterNamed(ClientApiConstants.fullnameParamName, this.fullname)) {
            final String newValue = command.stringValueOfParameterNamed(ClientApiConstants.fullnameParamName);
            actualChanges.put(ClientApiConstants.fullnameParamName, newValue);
            this.fullname = newValue;
        }

        if (command.isChangeInLongParameterNamed(ClientApiConstants.staffIdParamName, staffId())) {
            final Long newValue = command.longValueOfParameterNamed(ClientApiConstants.staffIdParamName);
            actualChanges.put(ClientApiConstants.staffIdParamName, newValue);
        }

        if (command.isChangeInLongParameterNamed(ClientApiConstants.genderIdParamName, genderId())) {
            final Long newValue = command.longValueOfParameterNamed(ClientApiConstants.genderIdParamName);
            actualChanges.put(ClientApiConstants.genderIdParamName, newValue);
        }

        if (command.isChangeInLongParameterNamed(ClientApiConstants.savingsProductIdParamName, savingsProductId())) {
            final Long newValue = command.longValueOfParameterNamed(ClientApiConstants.savingsProductIdParamName);
            actualChanges.put(ClientApiConstants.savingsProductIdParamName, newValue);
        }

        if (command.isChangeInLongParameterNamed(ClientApiConstants.genderIdParamName, genderId())) {
            final Long newValue = command.longValueOfParameterNamed(ClientApiConstants.genderIdParamName);
            actualChanges.put(ClientApiConstants.genderIdParamName, newValue);
        }

        if (command.isChangeInLongParameterNamed(ClientApiConstants.clientTypeIdParamName, clientTypeId())) {
            final Long newValue = command.longValueOfParameterNamed(ClientApiConstants.clientTypeIdParamName);
            actualChanges.put(ClientApiConstants.clientTypeIdParamName, newValue);
        }

        if (command.isChangeInLongParameterNamed(ClientApiConstants.clientClassificationIdParamName, clientClassificationId())) {
            final Long newValue = command.longValueOfParameterNamed(ClientApiConstants.clientClassificationIdParamName);
            actualChanges.put(ClientApiConstants.clientClassificationIdParamName, newValue);
        }
        
        if (command.isChangeInStringParameterNamed(ClientApiConstants.companyNameParamName, this.companyName)) {
            final String newValue = command.stringValueOfParameterNamed(ClientApiConstants.companyNameParamName);
            actualChanges.put(ClientApiConstants.companyNameParamName, newValue);
            this.companyName = StringUtils.defaultIfEmpty(newValue, null);
        }
        
        if (command.isChangeInStringParameterNamed(ClientApiConstants.contactNameParamName, this.contactName)) {
            final String newValue = command.stringValueOfParameterNamed(ClientApiConstants.contactNameParamName);
            actualChanges.put(ClientApiConstants.contactNameParamName, newValue);
            this.contactName = StringUtils.defaultIfEmpty(newValue, null);
        }
        
        if (command.isChangeInLongParameterNamed(ClientApiConstants.officePhoneParamName, this.officePhone)) {
            final Long newValue = command.longValueOfParameterNamed(ClientApiConstants.officePhoneParamName);
            actualChanges.put(ClientApiConstants.officePhoneParamName, newValue);
            this.officePhone = newValue;
        }
        
        if (command.isChangeInLongParameterNamed(ClientApiConstants.homePhoneParamName, this.homePhone)) {
            final Long newValue = command.longValueOfParameterNamed(ClientApiConstants.homePhoneParamName);
            actualChanges.put(ClientApiConstants.homePhoneParamName, newValue);
            this.homePhone = newValue;
        }
        
        if (command.isChangeInLongParameterNamed(ClientApiConstants.mobileParamName, this.mobile)) {
            final Long newValue = command.longValueOfParameterNamed(ClientApiConstants.mobileParamName);
            actualChanges.put(ClientApiConstants.mobileParamName, newValue);
            this.mobile = newValue;
        }
        
        if (command.isChangeInStringParameterNamed(ClientApiConstants.faxParamName, this.fax)) {
            final String newValue = command.stringValueOfParameterNamed(ClientApiConstants.faxParamName);
            actualChanges.put(ClientApiConstants.faxParamName, newValue);
            this.fax = StringUtils.defaultIfEmpty(newValue, null);
        }
        
        if (command.isChangeInStringParameterNamed(ClientApiConstants.emailParamName, this.email)) {
            final String newValue = command.stringValueOfParameterNamed(ClientApiConstants.emailParamName);
            actualChanges.put(ClientApiConstants.emailParamName, newValue);
            this.email = StringUtils.defaultIfEmpty(newValue, null);
        }
        
        if (command.isChangeInStringParameterNamed(ClientApiConstants.gstnRegNoParamName, this.gstnRegNo)) {
            final String newValue = command.stringValueOfParameterNamed(ClientApiConstants.gstnRegNoParamName);
            actualChanges.put(ClientApiConstants.gstnRegNoParamName, newValue);
            this.gstnRegNo = StringUtils.defaultIfEmpty(newValue, null);
        }
        
        if (command.isChangeInStringParameterNamed(ClientApiConstants.panNoParamName, this.panNo)) {
            final String newValue = command.stringValueOfParameterNamed(ClientApiConstants.panNoParamName);
            actualChanges.put(ClientApiConstants.panNoParamName, newValue);
            this.panNo = StringUtils.defaultIfEmpty(newValue, null);
        }
        
        if (command.isChangeInStringParameterNamed(ClientApiConstants.etinParamName, this.etin)) {
            final String newValue = command.stringValueOfParameterNamed(ClientApiConstants.etinParamName);
            actualChanges.put(ClientApiConstants.etinParamName, newValue);
            this.etin = StringUtils.defaultIfEmpty(newValue, null);
        }
        
        if (command.isChangeInStringParameterNamed(ClientApiConstants.addressLine1ParamName, this.addressLine1)) {
            final String newValue = command.stringValueOfParameterNamed(ClientApiConstants.addressLine1ParamName);
            actualChanges.put(ClientApiConstants.addressLine1ParamName, newValue);
            this.addressLine1 = StringUtils.defaultIfEmpty(newValue, null);
        }
        
        if (command.isChangeInStringParameterNamed(ClientApiConstants.addressLine2ParamName, this.addressLine2)) {
            final String newValue = command.stringValueOfParameterNamed(ClientApiConstants.addressLine2ParamName);
            actualChanges.put(ClientApiConstants.addressLine2ParamName, newValue);
            this.addressLine2 = StringUtils.defaultIfEmpty(newValue, null);
        }
        
        if (command.isChangeInStringParameterNamed(ClientApiConstants.cityParamName, this.city)) {
            final String newValue = command.stringValueOfParameterNamed(ClientApiConstants.cityParamName);
            actualChanges.put(ClientApiConstants.cityParamName, newValue);
            this.city = StringUtils.defaultIfEmpty(newValue, null);
        }
        
        if (command.isChangeInStringParameterNamed(ClientApiConstants.stateParamName, this.state)) {
            final String newValue = command.stringValueOfParameterNamed(ClientApiConstants.stateParamName);
            actualChanges.put(ClientApiConstants.stateParamName, newValue);
            this.state = StringUtils.defaultIfEmpty(newValue, null);
        }
        
        if (command.isChangeInStringParameterNamed(ClientApiConstants.countryParamName, this.country)) {
            final String newValue = command.stringValueOfParameterNamed(ClientApiConstants.countryParamName);
            actualChanges.put(ClientApiConstants.countryParamName, newValue);
            this.country = StringUtils.defaultIfEmpty(newValue, null);
        }
        
        if (command.isChangeInLongParameterNamed(ClientApiConstants.pinParamName, this.pin)) {
            final Long newValue = command.longValueOfParameterNamed(ClientApiConstants.pinParamName);
            actualChanges.put(ClientApiConstants.pinParamName, newValue);
            this.pin = newValue;
        }
        
        //
        
        if (command.isChangeInIntegerParameterNamed(ClientApiConstants.legalFormIdParamName, this.getLegalForm())) {
            final Integer newValue = command.integerValueOfParameterNamed(ClientApiConstants.legalFormIdParamName);
            if(newValue != null)
            {
            	LegalForm legalForm = LegalForm.fromInt(newValue);
            	if(legalForm != null)
            	{
            		actualChanges.put(ClientApiConstants.legalFormIdParamName, ClientEnumerations.legalForm(newValue));
                    this.setLegalForm(legalForm.getValue());
            	}
            	else
            	{
            		actualChanges.put(ClientApiConstants.legalFormIdParamName, null);
                    this.setLegalForm(null);
            	}
            }
            else
            {
            	actualChanges.put(ClientApiConstants.legalFormIdParamName, null);
                this.setLegalForm(null);
            }
        }

        final String dateFormatAsInput = command.dateFormat();
        final String localeAsInput = command.locale();

        if (command.isChangeInLocalDateParameterNamed(ClientApiConstants.activationDateParamName, getActivationLocalDate())) {
            final String valueAsInput = command.stringValueOfParameterNamed(ClientApiConstants.activationDateParamName);
            actualChanges.put(ClientApiConstants.activationDateParamName, valueAsInput);
            actualChanges.put(ClientApiConstants.dateFormatParamName, dateFormatAsInput);
            actualChanges.put(ClientApiConstants.localeParamName, localeAsInput);

            final LocalDate newValue = command.localDateValueOfParameterNamed(ClientApiConstants.activationDateParamName);
            this.activationDate = newValue.toDate();
            this.officeJoiningDate = this.activationDate;
        }

        if (command.isChangeInLocalDateParameterNamed(ClientApiConstants.dateOfBirthParamName, dateOfBirthLocalDate())) {
            final String valueAsInput = command.stringValueOfParameterNamed(ClientApiConstants.dateOfBirthParamName);
            actualChanges.put(ClientApiConstants.dateOfBirthParamName, valueAsInput);
            actualChanges.put(ClientApiConstants.dateFormatParamName, dateFormatAsInput);
            actualChanges.put(ClientApiConstants.localeParamName, localeAsInput);

            final LocalDate newValue = command.localDateValueOfParameterNamed(ClientApiConstants.dateOfBirthParamName);
            this.dateOfBirth = newValue.toDate();
        }

        if (command.isChangeInLocalDateParameterNamed(ClientApiConstants.submittedOnDateParamName, getSubmittedOnDate())) {
            final String valueAsInput = command.stringValueOfParameterNamed(ClientApiConstants.submittedOnDateParamName);
            actualChanges.put(ClientApiConstants.submittedOnDateParamName, valueAsInput);
            actualChanges.put(ClientApiConstants.dateFormatParamName, dateFormatAsInput);
            actualChanges.put(ClientApiConstants.localeParamName, localeAsInput);

            final LocalDate newValue = command.localDateValueOfParameterNamed(ClientApiConstants.submittedOnDateParamName);
            this.submittedOnDate = newValue.toDate();
        }

        validateUpdate();

        deriveDisplayName();

        return actualChanges;
    }

    private void validateNameParts(final List<ApiParameterError> dataValidationErrors) {
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("client");

        if (StringUtils.isNotBlank(this.fullname)) {

            baseDataValidator.reset().parameter(ClientApiConstants.firstnameParamName).value(this.firstname)
                    .mustBeBlankWhenParameterProvided(ClientApiConstants.fullnameParamName, this.fullname);

            baseDataValidator.reset().parameter(ClientApiConstants.middlenameParamName).value(this.middlename)
                    .mustBeBlankWhenParameterProvided(ClientApiConstants.fullnameParamName, this.fullname);

            baseDataValidator.reset().parameter(ClientApiConstants.lastnameParamName).value(this.lastname)
                    .mustBeBlankWhenParameterProvided(ClientApiConstants.fullnameParamName, this.fullname);
        } else {

            baseDataValidator.reset().parameter(ClientApiConstants.firstnameParamName).value(this.firstname).notBlank()
                    .notExceedingLengthOf(50);
            baseDataValidator.reset().parameter(ClientApiConstants.middlenameParamName).value(this.middlename).ignoreIfNull()
                    .notExceedingLengthOf(50);
            baseDataValidator.reset().parameter(ClientApiConstants.lastnameParamName).value(this.lastname).notBlank()
                    .notExceedingLengthOf(50);
        }
    }

    private void validateActivationDate(final List<ApiParameterError> dataValidationErrors) {

        if (getSubmittedOnDate() != null && isDateInTheFuture(getSubmittedOnDate())) {

            final String defaultUserMessage = "submitted date cannot be in the future.";
            final ApiParameterError error = ApiParameterError.parameterError("error.msg.clients.submittedOnDate.in.the.future",
                    defaultUserMessage, ClientApiConstants.submittedOnDateParamName, this.submittedOnDate);

            dataValidationErrors.add(error);
        }

        if (getActivationLocalDate() != null && getSubmittedOnDate() != null && getSubmittedOnDate().isAfter(getActivationLocalDate())) {

            final String defaultUserMessage = "submitted date cannot be after the activation date";
            final ApiParameterError error = ApiParameterError.parameterError("error.msg.clients.submittedOnDate.after.activation.date",
                    defaultUserMessage, ClientApiConstants.submittedOnDateParamName, this.submittedOnDate);

            dataValidationErrors.add(error);
        }

		if (getReopenedDate() != null && getActivationLocalDate() != null
				&& getReopenedDate().isAfter(getActivationLocalDate())) {

			final String defaultUserMessage = "reopened date cannot be after the submittedon date";
			final ApiParameterError error = ApiParameterError.parameterError(
					"error.msg.clients.submittedOnDate.after.reopened.date", defaultUserMessage,
					ClientApiConstants.reopenedDateParamName, this.reopenedDate);

			dataValidationErrors.add(error);
		}

        if (getActivationLocalDate() != null && isDateInTheFuture(getActivationLocalDate())) {

            final String defaultUserMessage = "Activation date cannot be in the future.";
            final ApiParameterError error = ApiParameterError.parameterError("error.msg.clients.activationDate.in.the.future",
                    defaultUserMessage, ClientApiConstants.activationDateParamName, getActivationLocalDate());

            dataValidationErrors.add(error);
        }

        if (getActivationLocalDate() != null) {
            if (this.office.isOpeningDateAfter(getActivationLocalDate())) {
                final String defaultUserMessage = "Client activation date cannot be a date before the office opening date.";
                final ApiParameterError error = ApiParameterError.parameterError(
                        "error.msg.clients.activationDate.cannot.be.before.office.activation.date", defaultUserMessage,
                        ClientApiConstants.activationDateParamName, getActivationLocalDate());
                dataValidationErrors.add(error);
            }
        }
    }

    private void deriveDisplayName() {

        StringBuilder nameBuilder = new StringBuilder();
        Integer legalForm = this.getLegalForm();
        if(legalForm == null || LegalForm.fromInt(legalForm).isPerson())
        {
        	if (StringUtils.isNotBlank(this.firstname)) {
                nameBuilder.append(this.firstname).append(' ');
            }

            if (StringUtils.isNotBlank(this.middlename)) {
                nameBuilder.append(this.middlename).append(' ');
            }

            if (StringUtils.isNotBlank(this.lastname)) {
                nameBuilder.append(this.lastname);
            }
            
            if (StringUtils.isNotBlank(this.fullname)) {
                nameBuilder = new StringBuilder(this.fullname);
            }
        }
        else if(LegalForm.fromInt(legalForm).isEntity())
        {
        	if (StringUtils.isNotBlank(this.fullname)) {
                nameBuilder = new StringBuilder(this.fullname);
            }
        }
        
        this.displayName = nameBuilder.toString();
    }

    public LocalDate getSubmittedOnDate() {
        return (LocalDate) ObjectUtils.defaultIfNull(new LocalDate(this.submittedOnDate), null);
    }

    public LocalDate getActivationLocalDate() {
        LocalDate activationLocalDate = null;
        if (this.activationDate != null) {
            activationLocalDate = LocalDate.fromDateFields(this.activationDate);
        }
        return activationLocalDate;
    }

    public LocalDate getOfficeJoiningLocalDate() {
        LocalDate officeJoiningLocalDate = null;
        if (this.officeJoiningDate != null) {
            officeJoiningLocalDate = LocalDate.fromDateFields(this.officeJoiningDate);
        }
        return officeJoiningLocalDate;
    }

    public boolean isOfficeIdentifiedBy(final Long officeId) {
        return this.office.identifiedBy(officeId);
    }

    public Long officeId() {
        return this.office.getId();
    }

    public void setImage(final Image image) {
        this.image = image;
    }

    public Image getImage() {
        return this.image;
    }

    public String mobileNo() {
        return this.mobileNo;
    }

    public void setMobileNo(final String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }

    public Office getOffice() {
        return this.office;
    }

    public Office getTransferToOffice() {
        return this.transferToOffice;
    }

    public void updateOffice(final Office office) {
        this.office = office;
    }

    public void updateTransferToOffice(final Office office) {
        this.transferToOffice = office;
    }

    public void updateOfficeJoiningDate(final Date date) {
        this.officeJoiningDate = date;
    }

    private Long staffId() {
        Long staffId = null;
        if (this.staff != null) {
            staffId = this.staff.getId();
        }
        return staffId;
    }

    public void updateStaff(final Staff staff) {
        this.staff = staff;
    }

    public Staff getStaff() {
        return this.staff;
    }

    public void unassignStaff() {
        this.staff = null;
    }

    public void assignStaff(final Staff staff) {
        this.staff = staff;
    }

    public Set<Group> getGroups() {
        return this.groups;
    }

    public void close(final AppUser currentUser, final CodeValue closureReason, final Date closureDate) {
        this.closureReason = closureReason;
        this.closureDate = closureDate;
        this.closedBy = currentUser;
        this.status = ClientStatus.CLOSED.getValue();
    }

    public Integer getStatus() {
        return this.status;
    }
    
    public CodeValue subStatus() {
        return this.subStatus;
    }
    
    public Long subStatusId() {
        Long subStatusId = null;
        if (this.subStatus != null) {
            subStatusId = this.subStatus.getId();
        }
        return subStatusId;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public boolean isActivatedAfter(final LocalDate submittedOn) {
        return getActivationLocalDate().isAfter(submittedOn);
    }

    public boolean isChildOfGroup(final Long groupId) {
        if (groupId != null && this.groups != null && !this.groups.isEmpty()) {
            for (final Group group : this.groups) {
                if (group.getId().equals(groupId)) { return true; }
            }
        }
        return false;
    }

    public Long savingsProductId() {
        return this.savingsProductId;
    }

    public void updateSavingsProduct(final Long savingsProductId) {
        this.savingsProductId = savingsProductId;
    }

    public AppUser activatedBy() {
        return this.activatedBy;
    }

    public Long savingsAccountId() {
        return this.savingsAccountId;
    }

    public void updateSavingsAccount(Long savingsAccountId) {
        this.savingsAccountId = savingsAccountId;
    }

    public Long genderId() {
        Long genderId = null;
        if (this.gender != null) {
            genderId = this.gender.getId();
        }
        return genderId;
    }

    public Long clientTypeId() {
        Long clientTypeId = null;
        if (this.clientType != null) {
            clientTypeId = this.clientType.getId();
        }
        return clientTypeId;
    }

    public Long clientClassificationId() {
        Long clientClassificationId = null;
        if (this.clientClassification != null) {
            clientClassificationId = this.clientClassification.getId();
        }
        return clientClassificationId;
    }

    public LocalDate getClosureDate() {
        return (LocalDate) ObjectUtils.defaultIfNull(new LocalDate(this.closureDate), null);
    }
    public LocalDate getRejectedDate() {
        return (LocalDate) ObjectUtils.defaultIfNull(new LocalDate(this.rejectionDate), null);
    }
    public LocalDate getWithdrawalDate() {
        return (LocalDate) ObjectUtils.defaultIfNull(new LocalDate(this.withdrawalDate), null);
	}

	public LocalDate getReopenedDate() {
		return this.reopenedDate == null ? null : new LocalDate(this.reopenedDate);
	}

	public CodeValue gender() {
        return this.gender;
    }

    public CodeValue clientType() {
        return this.clientType;
    }

    public void updateClientType(CodeValue clientType) {
        this.clientType = clientType;
    }

    public CodeValue clientClassification() {
        return this.clientClassification;
    }

    public void updateClientClassification(CodeValue clientClassification) {
        this.clientClassification = clientClassification;
    }

    public void updateGender(CodeValue gender) {
        this.gender = gender;
    }

    public Date dateOfBirth() {
        return this.dateOfBirth;
    }

    public LocalDate dateOfBirthLocalDate() {
        LocalDate dateOfBirth = null;
        if (this.dateOfBirth != null) {
            dateOfBirth = LocalDate.fromDateFields(this.dateOfBirth);
        }
        return dateOfBirth;
    }

    public void reject(AppUser currentUser, CodeValue rejectionReason, Date rejectionDate) {
        this.rejectionReason = rejectionReason;
        this.rejectionDate = rejectionDate;
        this.rejectedBy = currentUser;
        this.updatedBy = currentUser;
        this.updatedOnDate = rejectionDate;
        this.status = ClientStatus.REJECTED.getValue();

    }

    public void withdraw(AppUser currentUser, CodeValue withdrawalReason, Date withdrawalDate) {
        this.withdrawalReason = withdrawalReason;
        this.withdrawalDate = withdrawalDate;
        this.withdrawnBy = currentUser;
        this.updatedBy = currentUser;
        this.updatedOnDate = withdrawalDate;
        this.status = ClientStatus.WITHDRAWN.getValue();

    }

    public void reActivate(AppUser currentUser, Date reactivateDate) {
        this.closureDate = null;
        this.closureReason = null;
        this.reactivateDate = reactivateDate;
        this.reactivatedBy = currentUser;
        this.updatedBy = currentUser;
        this.updatedOnDate = reactivateDate;
        this.status = ClientStatus.PENDING.getValue();

    }
    
    public void reOpened(AppUser currentUser, Date reopenedDate) {
        this.reopenedDate = reopenedDate;
        this.reopenedBy = currentUser;
        this.updatedBy = currentUser;
        this.updatedOnDate = reopenedDate;
        this.status = ClientStatus.PENDING.getValue();

    }

    public Integer getLegalForm() {
        return legalForm;
    }

    public void setLegalForm(Integer legalForm) {
        this.legalForm = legalForm;
    }
    
    public void loadLazyCollections() {
        this.groups.size() ;
    }
    
    public String getFirstname(){return this.firstname;}

    public String getMiddlename(){return this.middlename;}

    public String getLastname(){return this.lastname;}
}