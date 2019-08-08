package com.facom.facomemfoco.presentation.structure.sl

class InstanceNotFoundException(
        message: String = "Instance required was not found"
) : RuntimeException(message)