/*
 * 
 * Copyright (C) 2013-2018 AT&T Intellectual Property.
 *
 * SPDX-License-Identifier: MIT
 *
 */
package com.att.research.xacml.admin.view.components;

import java.io.Serializable;
import java.util.Set;

import com.att.research.xacml.admin.XacmlAdminUI;
import com.att.research.xacml.admin.jpa.Attribute;
import com.att.research.xacml.admin.jpa.Category;
import com.att.research.xacml.admin.jpa.Datatype;
import com.att.research.xacml.admin.util.JPAUtils;
import com.att.research.xacml.admin.util.XACMLConstants;
import com.att.research.xacml.admin.view.events.AttributeChangedEventListener;
import com.att.research.xacml.admin.view.events.AttributeChangedEventNotifier;
import com.att.research.xacml.api.Identifier;
import com.att.research.xacml.api.XACML3;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.provider.CachingLocalEntityProvider;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class AttributeStandardSelectorComponent extends CustomComponent implements AttributeChangedEventNotifier {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_2;
	@AutoGenerated
	private Table tableDatatypes;
	@AutoGenerated
	private Table tableAttributes;
	@AutoGenerated
	private ComboBox comboBoxCategories;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public class AttributeBean implements Serializable {
		private static final long serialVersionUID = 1L;
		private Identifier	attributeId;
		private Identifier	categoryId;
		
		public AttributeBean(Identifier category, Identifier id) {
			this.categoryId = category;
			this.attributeId = id;
		}
		public Identifier getAttributeId() {
			return attributeId;
		}
		public void setAttributeId(Identifier attributeId) {
			this.attributeId = attributeId;
		}
		public Identifier getCategoryId() {
			return categoryId;
		}
		public void setCategoryId(Identifier categoryId) {
			this.categoryId = categoryId;
		}
		@Override
		public String toString() {
			return this.attributeId.stringValue();
		}
		
	}
	private final AttributeStandardSelectorComponent self = this;
	private final Datatype datatype;
	private final Attribute attribute;
	private final BasicNotifier notifier = new BasicNotifier();
	private static final JPAContainer<Category>	categories = new JPAContainer<Category>(Category.class);
	private static final JPAContainer<Datatype>	datatypes = new JPAContainer<Datatype>(Datatype.class);
	static {
		categories.setEntityProvider(new CachingLocalEntityProvider<Category>(Category.class, ((XacmlAdminUI)UI.getCurrent()).getEntityManager()));
		datatypes.setEntityProvider(new CachingLocalEntityProvider<Datatype>(Datatype.class, ((XacmlAdminUI)UI.getCurrent()).getEntityManager()));
		categories.sort(new String[]{"xacmlId"}, new boolean[]{true});
		datatypes.sort(new String[]{"xacmlId"}, new boolean[]{true});
	}
	private BeanItemContainer<AttributeBean> container = new BeanItemContainer<AttributeBean>(AttributeBean.class);
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public AttributeStandardSelectorComponent(Datatype datatype, Attribute initialAttribute) {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		//
		// Save
		//
		this.datatype = datatype;
		this.attribute = initialAttribute;
		//
		// Initialize GUI
		//
		this.initializeCategories();
		this.initializeAttributes();
		this.initializeDatatypes();
		//
		// Initialize focus
		//
		this.tableAttributes.focus();
	}
	
	private void initializeCategories() {
		//
		// Remove any filters
		//
		AttributeStandardSelectorComponent.categories.removeAllContainerFilters();
		//
		// Initialize data source & GUI properties
		//
		this.comboBoxCategories.setContainerDataSource(AttributeStandardSelectorComponent.categories);
		this.comboBoxCategories.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		this.comboBoxCategories.setItemCaptionPropertyId("xacmlId");
		this.comboBoxCategories.setImmediate(true);
		this.comboBoxCategories.setNullSelectionAllowed(false);
		//
		// Set default selection
		//
		Category defaultCategory;
		if (this.attribute == null || this.attribute.getCategoryBean() == null) {
			defaultCategory = JPAUtils.findCategory(XACML3.ID_SUBJECT_CATEGORY_ACCESS_SUBJECT);
		} else {
			defaultCategory = this.attribute.getCategoryBean();
		}
		if (defaultCategory != null) {
			this.comboBoxCategories.select(defaultCategory.getId());
		}
		//
		// Respond to events
		//
		this.comboBoxCategories.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				self.setupAttributeIDs();
				self.fireAttributeChanged(self.getAttribute());
			}
		});
	}
	
	private void initializeAttributes() {
		//
		// Initialize GUI properties
		//
		this.tableAttributes.setImmediate(true);
		this.tableAttributes.setNullSelectionAllowed(false);
		this.tableAttributes.setPageLength(10);
		//
		// Setup
		//
		this.tableAttributes.setContainerDataSource(this.container);
		this.tableAttributes.setVisibleColumns(new Object[] {"attributeId"});
		this.tableAttributes.setColumnHeaders(new String[] {"Attribute Id"});
		this.tableAttributes.setSelectable(true);
		this.setupAttributeIDs();
		//
		// Respond
		//
		this.tableAttributes.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				self.fireAttributeChanged(self.getAttribute());
			}
		});
	}
	
	private void initializeDatatypes() {
		//
		// Remove any filters
		//
		AttributeStandardSelectorComponent.datatypes.removeAllContainerFilters();
		//
		// Initialize data source & GUI properties
		//
		this.tableDatatypes.setContainerDataSource(AttributeStandardSelectorComponent.datatypes);
		this.tableDatatypes.setVisibleColumns(new Object[] {"shortName", "xacmlId"});
		this.tableDatatypes.setColumnHeaders(new String[] {"Short Name", "XACML Data Type ID"});
		this.tableDatatypes.setImmediate(true);
		this.tableDatatypes.setNullSelectionAllowed(false);
		this.tableDatatypes.setPageLength(10);
		//
		// Are we restricted to a certain datatype?
		//
		if (this.datatype != null) {
			//
			// Datatype restricted
			//
			this.tableDatatypes.select(this.datatype.getId());
			this.tableDatatypes.setEnabled(false);
			return;
		}
		this.tableDatatypes.setSelectable(true);
		//
		// Select a default
		//
		Datatype defaultDatatype;
		if (this.attribute == null || this.attribute.getDatatypeBean() == null) {
			defaultDatatype = JPAUtils.findDatatype(XACML3.ID_DATATYPE_STRING);
		} else {
			defaultDatatype = this.attribute.getDatatypeBean();
		}
		if (defaultDatatype != null) {
			this.tableDatatypes.select(defaultDatatype.getId());
		}
		//
		// Respond to events
		//
		this.tableDatatypes.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				self.fireAttributeChanged(self.getAttribute());
			}
		});
	}
	
	private void	setupAttributeIDs() {
		//
		// Get the current selection (should NOT be null)
		//
		Object id = this.comboBoxCategories.getValue();
		Category category = AttributeStandardSelectorComponent.categories.getItem(id).getEntity();
		Set<Identifier> attributeIDs = null;
		if (category.isStandard()) {
			//
			// Get the subset of attribute ID's specific to this standard
			// category.
			//
			attributeIDs = XACMLConstants.MAP_STANDARD_CATEGORIES.get(category.getIdentifer());
		}
		if (attributeIDs == null) {
			//
			// A custom category, there are no default attribute IDs
			// that we can determine.
			//
			attributeIDs = XACMLConstants.STANDARD_ATTRIBUTES;
		} 
		//
		// Reset attribute IDs. Based on the category, add only acceptable
		// attribute ID's for the category.
		//
		Object defaultBean = null;
		this.tableAttributes.removeAllItems();
		for (Identifier attributeId : attributeIDs) {
			BeanItem<AttributeBean> newItem = this.container.addItem(new AttributeBean(category.getIdentifer(), attributeId));
			if (this.attribute == null) {
				if (attributeId.equals(XACML3.ID_SUBJECT_SUBJECT_ID) ||
					attributeId.equals(XACML3.ID_ACTION_ACTION_ID) ||
					attributeId.equals(XACML3.ID_RESOURCE_RESOURCE_ID) ||
					attributeId.equals(XACML3.ID_ENVIRONMENT_CURRENT_DATE)) {
					defaultBean = newItem.getBean();
				}
			} else {
				if (newItem.getBean().getAttributeId().stringValue().equals(this.attribute.getXacmlId())) {
					defaultBean = newItem.getBean();
				}
			}
		}
		//
		// Set the default selection
		//
		if (defaultBean != null) {
			this.tableAttributes.select(defaultBean);
		}
	}
	
	@Override
	public void commit() {
		this.comboBoxCategories.commit();
		this.tableAttributes.commit();
		this.tableDatatypes.commit();
	}

	@Override
	public Attribute	getAttribute() {
		Object categoryId = this.comboBoxCategories.getValue();
		Object attributeId = this.tableAttributes.getValue();
		Object datatypeId = this.tableDatatypes.getValue();
		if (attributeId == null || datatypeId == null || categoryId == null) {
			return null;
		}
		AttributeBean attributeBean = this.container.getItem(attributeId).getBean();
		Datatype datatype = AttributeStandardSelectorComponent.datatypes.getItem(datatypeId).getEntity();
		Category category = AttributeStandardSelectorComponent.categories.getItem(categoryId).getEntity();
		if (datatype == null || category == null) {
			return null;
		}
		Attribute attribute = new Attribute(attributeBean.getAttributeId().stringValue());
		attribute.setCategoryBean(category);
		attribute.setDatatypeBean(datatype);
		
		return attribute;
	}

	@Override
	public boolean addListener(AttributeChangedEventListener listener) {
		return this.notifier.addListener(listener);
	}

	@Override
	public boolean removeListener(AttributeChangedEventListener listener) {
		return this.notifier.removeListener(listener);
	}

	@Override
	public void fireAttributeChanged(Attribute attribute) {
		this.notifier.fireAttributeChanged(attribute);
	}
	
	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");
		
		// comboBoxCategories
		comboBoxCategories = new ComboBox();
		comboBoxCategories.setCaption("Select A Category");
		comboBoxCategories.setImmediate(false);
		comboBoxCategories.setWidth("-1px");
		comboBoxCategories.setHeight("-1px");
		comboBoxCategories.setInvalidAllowed(false);
		comboBoxCategories.setRequired(true);
		mainLayout.addComponent(comboBoxCategories);
		mainLayout.setExpandRatio(comboBoxCategories, 1.0f);
		
		// horizontalLayout_2
		horizontalLayout_2 = buildHorizontalLayout_2();
		mainLayout.addComponent(horizontalLayout_2);
		mainLayout.setExpandRatio(horizontalLayout_2, 1.0f);
		
		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_2() {
		// common part: create layout
		horizontalLayout_2 = new HorizontalLayout();
		horizontalLayout_2.setImmediate(false);
		horizontalLayout_2.setWidth("-1px");
		horizontalLayout_2.setHeight("-1px");
		horizontalLayout_2.setMargin(false);
		horizontalLayout_2.setSpacing(true);
		
		// tableAttributes
		tableAttributes = new Table();
		tableAttributes.setCaption("Standard Attributes");
		tableAttributes.setImmediate(false);
		tableAttributes.setWidth("-1px");
		tableAttributes.setHeight("-1px");
		tableAttributes.setInvalidAllowed(false);
		tableAttributes.setRequired(true);
		horizontalLayout_2.addComponent(tableAttributes);
		
		// tableDatatypes
		tableDatatypes = new Table();
		tableDatatypes.setCaption("Standard Data Types");
		tableDatatypes.setImmediate(false);
		tableDatatypes.setWidth("-1px");
		tableDatatypes.setHeight("-1px");
		tableDatatypes.setInvalidAllowed(false);
		tableDatatypes.setRequired(true);
		horizontalLayout_2.addComponent(tableDatatypes);
		
		return horizontalLayout_2;
	}

}
