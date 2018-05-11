from setuptools import setup

setup(name='stanfordcorenlp-python',
      version='0.1',
      description='Python wrapper for Stanford CoreNLP',
      classifiers=[
          'Programming Language :: Python :: 2.7'
      ],
      url='https://github.com/plotlabs/stanfordcorenlp-python.git',
      keywords=["stanford", "coreNLP",
                "nlp", "stanfordcorenlp",
                "openie", "information extraction", "ie"],
      author='Vedanshi Kataria',
      author_email='vedanshi12117@iiitd.ac.in',
      license='LICENSE.txt',
      packages=['stanfordcorenlp-python'],
      include_package_data=True,
      zip_safe=False,
      install_requires=['jpype']
      )