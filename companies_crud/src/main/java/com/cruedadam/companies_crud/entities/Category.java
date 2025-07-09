package com.cruedadam.companies_crud.entities;

// Esta clase enum representa las posibles categorías a las que puede pertenecer una compañía.
// Al usar un enum en lugar de un String, se reduce la posibilidad de errores y se mejora la claridad del dominio.
public enum Category {
    SOCIAL_NETWORK,
    SERVICES,
    STREAMING,
    CLOUD_COMPUTING,
    DEVICES,
    EDUCATION,
    NONE
}
