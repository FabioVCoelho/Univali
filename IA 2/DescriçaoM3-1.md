A base de dados possui 1.349.072 com 46 colunas onde todas as colunas estão sendo tratadas como strings. Possui as seguintes colunas:

    'AnoCalendario', 'DataArquivamento', 'DataAbertura', 'CodigoRegiao',
    'Regiao', 'UF', 'strRazaoSocial', 'strNomeFantasia', 'Tipo',
    'NumeroCNPJ', 'radicalCNPJ', 'RazaoSocialRFB', 'NomeFantasiaRFB',
    'CNAEPrincipal', 'DescCNAEPrincipal', 'Atendida', 'CodigoAssunto',
    'DescricaoAssunto', 'CodigoProblema', 'DescricaoProblema',
    'SexoConsumidor', 'FaixaEtariaConsumidor', 'CEPConsumidor',
    'RadicalCNPJ', 'cep', 'tipo', 'nome_logradouro', 'logradouro', 'bairro',
    'cidade', 'estado', 'complemento', 'grandes_usuarios',
    'tipo_sem_acento', 'nome_logradouro_sem_acento',
    'logradouro_sem_acento', 'bairro_sem_acento', 'cidade_sem_acento',
    'complemento_sem_acento', 'grandes_usuarios_sem_acento', 'latitude',
    'longitude', 'cidade_ibge', 'cidade_area', 'ddd', 'cep_ativo'

Algumas colunas possuem o mesmo valor, sendo um delas pássiveis de serem deletas, tais como UF e estado, cep e CEPConsumidor.

Após a limpeza dos dados feita no primeiro projeto, como de esperado as colunas que seriam utilizadas para classificações estão sem valores nulos.

| Coluna | Valores nulos|
|---------|----------------|
|AnoCalendario                |        0|
|DataArquivamento             |        5|
|DataAbertura                 |        0|
|CodigoRegiao                 |        0|
|Regiao                       |        0|
|UF                           |        0|
|strRazaoSocial               |        0|
|strNomeFantasia              |   219838|
|Tipo                         |        0|
|NumeroCNPJ                   |    56924|
|radicalCNPJ                  |  1276810|
|RazaoSocialRFB               |    73690|
|NomeFantasiaRFB              |   744374|
|CNAEPrincipal                |    76903|
|DescCNAEPrincipal            |    91058|
|Atendida                     |        0|
|CodigoAssunto                |        0|
|DescricaoAssunto             |        0|
|CodigoProblema               |    19864|
|DescricaoProblema            |    19864|
|SexoConsumidor               |      969|
|FaixaEtariaConsumidor        |        0|
|CEPConsumidor                |        0|
|RadicalCNPJ                  |   130358|
|tipo                         |   153925|
|nome_logradouro              |   154868|
|logradouro                   |   153925|
|bairro                       |   155841|
|cidade                       |        0|
|complemento                  |  1136037|
|grandes_usuarios             |  1343997|
|tipo_sem_acento              |   153925|
|nome_logradouro_sem_acento   |   154868|
|logradouro_sem_acento        |   153925|
|bairro_sem_acento            |   155841|
|cidade_sem_acento            |        0|
|complemento_sem_acento       |  1136037|
|grandes_usuarios_sem_acento  |  1343997|
|latitude                     |        0|
|longitude                    |        0|
|cidade_ibge                  |        0|
|cidade_area                  |        0|
|ddd                          |        0|
|cep_ativo                    |        0|

Dentro dessas colunas, irei elencar as análises descritivas das colunas utilizadas para a classificação por simplicidade de avaliação

## Código Assunto

| Código Assunto | Frequência | Porcentagem % |
|----:|-------------:|----------------:|
| 101 | 155617 | 11.5351 |
| 187 | 89452 | 6.63063 | 
| 102 | 87629 | 6.4955 | 
| 54 | 75709 | 5.61193 | 
| 186 | 75662 | 5.60845 | 
| ... | .... | ..... |
| 151 | 1 | 7.4125e-05 |
| 47 | 1 | 7.4125e-05 |
| 160 | 1 | 7.4125e-05 |
| 52 | 1 | 7.4125e-05 |
| 209 | 1 | 7.4125e-05 |

## Código Problema

|Código Problema| Frequência    |Porcentagem %|
|----:|-------------:|----------------:|
|105|    252041|    18.961743|
|102|    164576|    12.381508|
|28|    128871|    9.695322|
|134|    117889|    8.869116|
|107|    66451|    4.999293|
|...|    ...|    ...|
|4817|    1    |0.000075|
|1882|    1    |0.000075|
|5566|    1    |0.000075|
|2943|    1    |0.000075|
|3633|    1    |0.000075|

## CEP

|CEP    |Frequência    |Porcentagem %|
|----:|-------------:|----------------:|
|75690000|    6205|    0.459946|
|78455000|    4643|    0.344163|
|78580000|    4062|    0.301096|
|68515000|    4007|    0.297019|
|89120000|    3853|    0.285604|
|...|    ...|    ...|
|64028535|    1|    0.000074|
|55008231|    1|    0.000074|
|53413835|    1|    0.000074|
|72130420|    1|    0.000074|
|08474360|    1|    0.000074|

## Cidade

|Cidade|    Frequência    |Porcentagem %|
|----:|-------------:|----------------:|
|São Paulo|    151812|    11.253069|
|Salvador|    104229|    7.725978|
|Campo Grande|    57526|    4.264116|
|Recife    |47367|    3.511080|
|Rio de Janeiro|    45947|    3.405823|
|... ...|    ...|
|Jati	1|    0.000074|
|Sátiro Dias|    1|    0.000074|
|Romaria|    1|    0.000074|
|Carrancas|    1|    0.000074|
|Conceição de Jacareí (Mangaratiba)|    1|    0.000074|

