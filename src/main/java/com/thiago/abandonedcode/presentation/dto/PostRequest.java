package com.thiago.abandonedcode.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequest(

    @NotBlank(message = "Título não pode ser vazio")
    @Size(min = 5, max = 200, message = "Título deve ter entre 5 e 200 caracteres")
    String title,

    @NotBlank(message = "Conteúdo não pode ser vazio")
    String content,

    String excerpt,

    Long categoryId,

    String featureImage
) {}
