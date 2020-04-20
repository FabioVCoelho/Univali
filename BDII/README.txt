Irei utilizar a idéia do nosso projeto, onde iremos fazer crawler de diversas fontes, em busca de padrões para formas de publicação de pessoas famosas nessas plataformas e recriar um bot para seguir os mesmos padrões dessas pessoas a fim de buscar uma maior quantidade de seguidores e monetizar através da plataforma.
Para isso será criada uma tabela de sites, onde irão ficar gravadas as urls das pessoas que mais possuem seguidores, se será buscado pelos seguidores através do atributo (depth), e quando que a página será revisitada pelo atributo (periodicity_in_minutes) para a busca de novas publicações, e a categoria do mesmo, por enquanto foram feitas tabelas apenas para o twitter,instagram e formulário geral. Para a tabela de cada uma dessas categorias as colunas correspondente são atributos necessários para acessar as plataformas.
Para a inserção e atualização dos sites foi criado uma trigger para validar se a url possue um protocolo de acesso ("http://" e "https://"), pois o mesmo facilita na hora de realizar o crawler. Se o depth é maior ou igual a 0, pois o mesmo indica quantas páginas de profundidade será buscada apartir da url indicada. E se a periodicidade é maior que 30, para não ficar buscando a mesma url em tão pouco tempo.
Foi criada outra trigger para fazer um log de alteração da tabela do site.
Para as funções foi criada uma para saber qual o site deve ser processado fazendo o diff das datas, da última vez processado e a data atual, retornando somente os que são necessário reprocessar. E para fazer o diff das datas foi utilizada uma função de DATEDIFF onde o mesmo faz a diferença entre as datas.
Para popular a tabela, foi criada uma tabela em .odt com 3000+ linhas.
Após a inserção dos mesmos, o select na tabela está demorando em torno de 70~90ms
SELECT * FROM site;

DELETE FROM site WHERE id > 50 AND id < 200;
DELETE FROM site WHERE id > 500 AND id < 700;
DELETE FROM site WHERE id > 720 AND id < 800;
DELETE FROM site WHERE id > 1000 AND id < 1200;
DELETE FROM site WHERE id > 1500 AND id < 1800;
DELETE FROM site WHERE id > 1923 AND id < 2112;
DELETE FROM site WHERE id > 2200 AND id < 2358;
DELETE FROM site WHERE id > 2643 AND id < 2943;

Após a deleção de algumas linhas é verificado que o mesmo select começa a demorar em torno de 90~120ms e que existem 400+ linhas deletadas através do ANALYZE VERBOSE site
E após realizar um VACUUM VERBOSE site a query volta ao valor padrão de 70~90ms
