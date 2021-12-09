import pandas as pd
from sklearn import preprocessing


class DataManipulation:

    def __init__(self):
        self.scaler = preprocessing.StandardScaler()

    def load_all_data(self):
        rec2009 = pd.read_csv("../data/reclamacoes-fundamentadas-sindec-2009.csv", header=0, encoding="ISO-8859-9", delimiter=";", dtype="str")
        rec2010 = pd.read_csv("../data/reclamacoes-fundamentadas-sindec-2010.csv", header=0, encoding="ISO-8859-9", delimiter=";", dtype="str")
        rec2011 = pd.read_csv("../data/reclamacoes-fundamentadas-sindec-2011.csv", header=0, encoding="ISO-8859-9", delimiter=";", dtype="str")
        rec2012 = pd.read_csv("../data/reclamacoes-fundamentadas-sindec-2012.csv", header=0, encoding="UTF-8", delimiter=";", dtype="str")
        rec2013 = pd.read_csv("../data/reclamacoes-fundamentadas-sindec-2013.csv", header=0, encoding="UTF-8", delimiter=";", dtype="str")
        rec2014 = pd.read_csv("../data/reclamacoes-fundamentadas-sindec-2014.csv", header=0, encoding="UTF-8", delimiter=";", dtype="str")
        rec2015 = pd.read_csv("../data/reclamacoes-fundamentadas-sindec-2015.csv", header=0, encoding="UTF-8", delimiter=";", dtype="str")
        rec2016 = pd.read_csv("../data/reclamacoes-fundamentadas-sindec-2016_v2.csv", header=0, encoding="UTF-8", delimiter=";", dtype="str")
        rec2017 = pd.read_csv("../data/CNRF_2017.csv", header=0, encoding="UTF-8", delimiter=";", dtype="str")
        rec2018 = pd.read_csv("../data/CNRF2018.csv", header=0, encoding="UTF-8", delimiter=";", dtype="str")
        rec2009 = self.rename_column("anocalendario", "AnoCalendario", rec2009)
        rec2009 = self.rename_column("codigoregiao", "CodigoRegiao", rec2009)
        rec2009 = self.rename_column("regiao", "Regiao", rec2009)
        rec2009 = self.rename_column("radicalCNPJ", "RadicalCNPJ", rec2009)
        rec2010 = self.rename_column("anocalendario", "AnoCalendario", rec2010)
        rec2010 = self.rename_column("codigoregiao", "CodigoRegiao", rec2010)
        rec2010 = self.rename_column("regiao", "Regiao", rec2010)
        rec2010 = self.rename_column("radicalCNPJ", "RadicalCNPJ", rec2010)
        rec2011 = self.rename_column("anocalendario", "AnoCalendario", rec2011)
        rec2011 = self.rename_column("codigoregiao", "CodigoRegiao", rec2011)
        rec2011 = self.rename_column("regiao", "Regiao", rec2011)
        rec2011 = self.rename_column("radicalCNPJ", "RadicalCNPJ", rec2011)
        frames = [rec2009, rec2010, rec2011, rec2012, rec2013, rec2014, rec2015, rec2016, rec2017, rec2018]
        full_dataframe = pd.concat(frames)
        return full_dataframe

    def rename_column(self, old_column_name, new_column_name, dataframe):
        return dataframe.rename(columns={old_column_name: new_column_name})

    def view_df_stats(self, dataframe):
        print("Shape of dataframe={}".format(dataframe.shape))
        print("Number of index levels:{}".format(dataframe.index.nlevels))
        for i in range(dataframe.index.nlevels):
            print("For index level {},unique values count={}".format(i, dataframe.index.get_level_values(i).unique().shape[0]))
        print("Columns of dataframe={}".format(dataframe.columns))
        print("Null count= \n {}".format(dataframe.isnull().sum()))
        print(dataframe.describe())

    def save_dataframe(self, path_to_save, filename, dataframe):
        dataframe.to_csv(path_to_save + "/" + filename + ".csv", index=False)

    def load_all_cep(self):
        dataframe = pd.read_csv("../data/tbl_cep_201805_csv.csv", header=0, encoding="ISO-8859-9", delimiter=",", dtype="str")
        dataframe['cidade_bairro'] = dataframe['cidade'] + "/" + dataframe['bairro']
        return dataframe

    def load_data(self, file_path):
        return pd.read_csv(file_path)

    def merge_dataframes(self, dataframe1, dataframe2, column_dataframe1, column_dataframe2):
        return pd.merge(dataframe1, dataframe2, left_on=column_dataframe1, right_on=column_dataframe2)

    def save_sample(self, path_to_save, filename, dataframe):
        dataframe.sample(frac=0.3).to_csv(path_to_save + "/" + filename + ".csv", index=False)

    def generate_dataframe_standard(self, dataframe, columns_to_analyse):
        dataframe["Atendida"] = self.convert_to_numeric("Atendida", dataframe)
        dataframe["cidade_bairro"] = self.convert_to_numeric("cidade_bairro", dataframe)
        dataframe_standard = self.transform_to_standard_scale(dataframe, columns_to_analyse)
        dataframe_standard = dataframe_standard.dropna(subset=columns_to_analyse)
        return dataframe_standard

    def convert_to_numeric(self, column, dataframe):
        tmp_df = dataframe.copy()
        attribute_classified = self.generate_unique_map(column, tmp_df)
        return tmp_df.apply(self.class_attribute, axis=1, args=(column, attribute_classified))

    def generate_unique_map(self, column, dataframe):
        attribute_numeric = {}
        unique_cities = dataframe[column].unique()
        for i in range(len(dataframe[column].unique())):
            attribute_numeric[unique_cities[i]] = i
        return attribute_numeric

    def class_attribute(self, row, column, attribute_classified):
        return attribute_classified[row[column]]

    def transform_to_standard_scale(self, dataframe, columns):
        tmp_df = dataframe[columns].copy()
        tmp_scaler = self.scaler.fit_transform(tmp_df[columns])
        return pd.DataFrame(tmp_scaler, columns=tmp_df.columns)

    def unique(self, dataframe, param):
        return pd.unique(dataframe[param])
